package com.lzw.ys7;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.videogo.openapi.EZOpenSDK;

/**
 * @author: lzw
 * @date: 13/12/2017 2:30 PM
 * @desc:
 */

public class Ys7App extends Application {

    private final String appKey = "4dd7877b948840fab257d7e0fc3385d9";

    private static Ys7App ys7App;

    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        Ys7App application = (Ys7App) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ys7App = this;

        refWatcher = LeakCanary.install(this);
//        /**
//         * 初始化EZUIKit
//         * @param application 应用application
//         * @param appkey      开发者申请的appkey
//         * @return
//         */
//        EZUIKit.initWithAppKey(this, appKey);
//        /**
//         * 设置授权accesstoken，当app获取到token后需要传入到EZUIKit，底层sdk获得授权
//         * @param accessToken
//         */
//        EZUIKit.setAccessToken("at.bkf7hu552tosffvb39qf2kv9a6frhfsv-54v03xiz4c-0kozjl5-6r0tkrev8");
    }

    public static Ys7App getYs7App() {
        return ys7App;
    }

    public void initEzOpenSDK() {
        /**
         * SDK完整接入
         */
        EZOpenSDK.showSDKLog(true);
        EZOpenSDK.enableP2P(false);
        EZOpenSDK.initLib(this, appKey);
    }

}
