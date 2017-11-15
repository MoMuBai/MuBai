package com.mb.mubai;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;


import com.lzw.library.utils.L;
import com.lzw.library.utils.OkHttpClientUtil;
import com.lzw.library.utils.SpUtils;
import com.lzw.library.utils.To;
import com.mb.mubai.base.util.MethodInfo;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/4
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class App extends MultiDexApplication {

    private static Context mContext;

    /**
     * 维护Activity的List
     */
    private static List<Activity> mActivitys = Collections.synchronizedList(new LinkedList<Activity>());


    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        mContext = getApplicationContext();
        strictMode();
        registerActivityListener();
        SpUtils.init(mContext);
        L.setL(Config.isPrintLog);
        To.init(mContext);
        try {
            OkHttpClientUtil.getInstance()
                    .setCertificates(getAssets().open("srca.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //配置程序异常退出处理
//        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));

        registerComponentCallbacks(new ComponentCallbacks() {

            /**
             * 监听 应用程序 配置信息的改变，如屏幕旋转等
             *
             * @param newConfig
             */
            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {

            }
        });
    }

    /**
     * 监听 Android系统整体内存较低时刻
     * Android 4.0前 检测内存使用情况，从而避免被系统直接杀掉 & 优化应用程序的性能体验
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    /**
     * 通知应用程序当前内存的使用情况
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    /**
     * 应用程序结束时调用
     * 但该方法只用于Android仿真机测试，在Android产品机是不会调用的
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * 获取Application唯一实例
     */
    public static Context getInstance() {
        return mContext;
    }

    /**
     * 严格模式检测程序中违例情况
     * 包括磁盘操作和内存问题
     */
    private void strictMode() {

        if (BuildConfig.DEBUG) {
            // 用于发现UI线程中是否有读写磁盘操作，是否有网络操作，检查UI线程中调用的自定义代码是否执行得比较慢
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()  // 磁盘读取
                    .detectDiskWrites() //磁盘写入
                    .detectNetwork()   // 网络请求
                    .penaltyLog()      //将警告输出到LogCat
                    .build());

            // 用于发现内存问题
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects() // sqlite对象没有正确关闭
                    .detectLeakedClosableObjects()//cursor对象有没有正确关闭
                    .penaltyLog()//将警告输出到LogCat
                    .penaltyDeath()//一旦StrictMode消息被写到LogCat后应用就会崩溃
                    .build());
        }
    }

    /**
     * 注册ActivityListener
     */
    private void registerActivityListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    if (null == mActivitys) {
                        return;
                    }
                    mActivitys.add(activity);
                }

                @Override
                public void onActivityStarted(Activity activity) {
                }

                @Override
                public void onActivityResumed(Activity activity) {
                }

                @Override
                public void onActivityPaused(Activity activity) {
                }

                @Override
                public void onActivityStopped(Activity activity) {
                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    if (null == activity && mActivitys.isEmpty()) {
                        return;
                    }
                    if (mActivitys.contains(activity)) {
                        mActivitys.remove(activity);
                    }
                }
            });
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */

    @MethodInfo(author = "quicklymost@gmail.com"
            , date = "2017-11-14", Desc = "获得当前进程的名字")
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
