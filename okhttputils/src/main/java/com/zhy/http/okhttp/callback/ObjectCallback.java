package com.zhy.http.okhttp.callback;

import com.google.gson.Gson;

import okhttp3.Response;

/**
 * Created by yiwei on 16/3/31.
 */
public abstract class ObjectCallback<T> extends Callback<T> {
    private Class<T> cls;
    public ObjectCallback(Class<T> cls){
        this.cls = cls;
    }

    @Override
    public T parseNetworkResponse(Response response) throws Exception {

        String json = response.body().string();
        if(cls == String.class){
            return (T)json;
        }
        return new Gson().fromJson(json, cls);
    }

}
