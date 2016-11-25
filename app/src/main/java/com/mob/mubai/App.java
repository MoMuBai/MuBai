package com.mob.mubai;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.mob.mubai.base.utils.L;
import com.mob.mubai.base.utils.SpUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by lzw on 2016/11/4.
 */

public class App extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        mContext = getApplicationContext();

        SpUtils.init(this);
        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);

            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            }
        }
    }

    /*
     * 获取Application唯一实例
     */
    public static Context getInstance() {
        return mContext;
    }


    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }

}
