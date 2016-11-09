package com.mob.mubai.base.utils;

import android.content.Context;
import android.os.Environment;



/**
 * Created by mubai on 2016/9/12.
 * 应用程序配置类：用于保存用户相关信息及设置
 */
public final class AppConfig {

    private static AppConfig appConfig;
    private static Context mContext;

    // 设置是否打印Log
    public static boolean isPrintLog = true;

    // 设置内外网切换:ture:内网，false:外网
    public static String netUrl = selelctNetwoek(false);

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


    /**
     * 切换网络内外网
     * ture：外网  false:外网
     */
    public static String selelctNetwoek(Boolean whatNetwork) {
        String netUrl = "";
        if (whatNetwork) {

        } else {

            netUrl = "http://192.168.1.43/dataApi/lzgApi.php?r=";
        }
        return netUrl;
    }

}
