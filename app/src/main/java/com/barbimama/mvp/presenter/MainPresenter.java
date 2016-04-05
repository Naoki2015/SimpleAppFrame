package com.barbimama.mvp.presenter;

import android.text.TextUtils;

import com.barbimama.manager.KeyManager;
import com.barbimama.mvp.model.LocationModel;
import com.barbimama.mvp.view.MainView;
import com.library.cache.CacheManager;
import com.zhy.http.okhttp.callback.ObjectCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by yiwei on 16/3/31.
 */
public class MainPresenter extends BasePresenter{

    private MainView view;

    public MainPresenter(MainView view){
        tag = getClass().getSimpleName();
        this.view = view;
    }

    public void loadLocation(){
        String url = "http://gc.ditu.aliyun.com/regeocoding?l=39.938133,116.395739&type=001";
        LocationModel.loadLocation(url, tag, new ObjectCallback<String>(String.class) {

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                view.showLoading("正在加载...");
            }

            @Override
            public void onAfter() {
                super.onAfter();
                view.hideLoading();
            }

            @Override
            public void onError(Call call, Exception e) {
                view.showError(e.toString());
            }

            @Override
            public void onResponse(String response) {
                view.updateLoadLocation(response);

                if (!TextUtils.isEmpty(response)) {
                    //缓存数据
                    CacheManager.writeObject(KeyManager.getLocationCacheKey(), response);
                }
            }
        });
    }

    public void loadLocationCache(){
        //读缓存
        String cache = CacheManager.readObject(KeyManager.getLocationCacheKey());
        view.updateLoadLocation(cache);
    }


}
