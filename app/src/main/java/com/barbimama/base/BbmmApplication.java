package com.barbimama.base;

import android.app.Application;

import com.barbimama.manager.ActivityManager;
import com.library.cache.CacheManager;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by yiwei on 16/4/5.
 */
public class BbmmApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // 设置默认缓存路径
        CacheManager.setSysCachePath(getCacheDir().getPath());
        OkHttpUtils.getInstance().debug("OkHttpUtils").setConnectTimeout(100000, TimeUnit.MILLISECONDS);

        //imageload 配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //.showImageForEmptyUri(R.drawable.im_skin_icon_imageload_default)
                //.showImageOnFail(R.drawable.im_skin_icon_imageload_failed)
                //.showImageOnLoading(R.drawable.im_skin_icon_imageload_default)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(200)
                .diskCache(new UnlimitedDiskCache(new File(Constants.DEFAULT_SAVE_IMAGE_PATH)))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void exitApp(){
        ActivityManager.getInstance().clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
