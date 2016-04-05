package com.barbimama.mvp.model;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by yiwei on 16/3/31.
 */
public class LocationModel {

    public static void loadLocation(String url, String tag, Callback<String> callback){
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .build()
                .execute(callback);
    }
}
