package com.barbimama.base;

import android.os.Environment;

import java.io.File;

/**
 * Created by yiwei on 16/4/5.
 */
public class Constants {
    public final static String ROOT = "Barbimama";
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + ROOT + File.separator + "Images"
            + File.separator;
}
