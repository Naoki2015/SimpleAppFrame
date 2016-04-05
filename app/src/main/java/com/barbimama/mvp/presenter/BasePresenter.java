package com.barbimama.mvp.presenter;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by yiwei on 16/4/1.
 */
public class BasePresenter {
    protected String tag;

    public void cancleRequest(){
        cancleRequest(tag);
    }

    public void cancleRequest(String tag){
        OkHttpUtils.getInstance().cancelTag(tag);
    }
}
