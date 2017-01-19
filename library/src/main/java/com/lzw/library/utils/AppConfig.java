package com.lzw.library.utils;

import android.content.Context;
import android.os.Environment;


/**
 * Created by lzw on 2016/11/8.
 * 应用程序配置类：用于保存用户相关信息及设置
 */
public final class AppConfig {

        private static AppConfig appConfig;
        private static Context mContext;

        // 设置是否打印Log
        public static boolean isPrintLog = true;

        // 拍照存放的路径
        public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath()
                  + "/mubai/camera/";


        public static AppConfig getAppConfig(Context context) {
                if (appConfig == null) {
                        appConfig = new AppConfig();
                        appConfig.mContext = context;
                }
                return appConfig;
        }

}
