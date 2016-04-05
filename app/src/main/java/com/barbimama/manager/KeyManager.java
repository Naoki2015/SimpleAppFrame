package com.barbimama.manager;

/**
 * Created by yiwei on 16/4/1.
 */
public class KeyManager {

    private static final String KEY_LOCATION = "_location_cache";

    private static String loginUserId;

    public static void setLoginUserId(String id){
        loginUserId = id;
    }

    public static String getLocationCacheKey(){
        return loginUserId + KEY_LOCATION;
    }
}
