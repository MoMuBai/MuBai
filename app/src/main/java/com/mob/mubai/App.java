package com.mob.mubai;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by mubai on 2016/11/4.
 */

public class App extends MultiDexApplication {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        mContext = getApplicationContext();
    }

    /*
  * 获取Application唯一实例
  */
    public static Context getInstance() {
        return mContext;
    }

}
