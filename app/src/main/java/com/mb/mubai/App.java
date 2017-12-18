package com.mb.mubai;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.IntDef;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;


import com.lzw.library.utils.L;
import com.lzw.library.utils.OkHttpClientUtil;
import com.lzw.library.utils.SpUtils;
import com.lzw.library.utils.To;
import com.mb.mubai.base.util.AnnotationUse;
import com.mb.mubai.base.util.MethodInfo;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

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

    private String version;

    private String appSerret = "cdf2a71c11b73fbadf15d55aaeb66f05";

    private String RSA_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCW+P2Wp7+wULaktuV7C6JR4u99tnxl3kJTRw6UkxBsMNH252KyGL+bA2dBF5W/S2aYxbhgnbcwbfl1/H7l1GQ4dAKDOsxe3wqb+OVSaBCpsJi6TQQL7cPAuDzPS1L0vHpLGDLwy3LjxQe7HYz0tDSk6ySBj2lHMkVXXJ4F27DvQl+QOoZOTx9SzBC3vrCfKJuftkJrGuEdxiCq0r9mBz9ByakiphfqOg0q5hOre7czBK+TgfmVPc4xx4tLEMVdlJM8qThgFYX0nrhiuGk+ZIH9XXygzXzhrD4yC2m9pXE74CJQevqYhV15Cg/Z7wen/WkmOLEsTjmH+R5VOLjIy9fPAgMBAAECggEATRc9noVwC59bdEZ0bd1l+HxyIHZjlTzmD1I0bsed1jEjtfk3W4vpo0Ucn49khvcAn7guyx8d4Nv3SzkwrzlCJ9WNRudqXnN0Hmr85pR/Fbc4dcFH9LNnhuxNGOj0pkXsCJaFxJGOzCHbLcAMLxQp4aC01nedoZ5Y7CA+DO2+rp0iHbu+cNMR5uWb5XffkbcV4QQFoNQ7JgfGandpkHiSNOe1DAuxLPDvakPuP10PI05ItnkBEQJakJWEmwviSsEAp7pcHfmkJ6zWtRR8xNEPiuxvIlS3UE5KcjkpKcE4/haUbVdXgSexCaqrkOdx6NPOEIgzggDM5Sp8rIuOpRMdcQKBgQDiRqPdbzyFwQ9Guug0Y4FbFovnIvh4KTvCkNixUjnkB9IOQiCgWf5An1q/IAtJlzeL/SHVwBPPEnpAjl9wq2S0qEkCJKM+lcwkFDF9WglLPuPdf6cdpnU/ICGBs4QR+cy2WRb3TG9NcICy81RqkMJly4UB9QuRPcYChCW3L5fD6QKBgQCqzf/rWe6STWAFjFtwMBEJUaLtcoPqDwJjp0MOf71VIDvnEh3s79UDh7tlxwNqN4o/yU9aK0JXaK2SFwZ2cxy96sW5xIZT78NYfmPm4wDrsyVb2fhE/BeDAm78mlNpSNtaQAT9CyVKuEEb4YJVq5e006Kh/OHtOOiAZVNFR2YC9wKBgFT63K3cCend0MPqjCyotjskY5SB8LkIG1Z+GbO2wuEeAsiyExjF05QnhOhEu5h+vzDG938UbRwWv20hSzzAUcj7brfsVJa7oXhnURLXzqfAzckxGO0sx9nWUl8osVAi9bwCB3sG7MIzn1IVyr1e8nNSlPhgNGDuxz/Iq3V53VCJAoGAa8vg1RcH4xukGzxOxQoA5oG4MuVP7ZsVZfE899yUkDHsvFOJwWxHYpf6JCyqshFlVgZl50Ff+CxfRtDzSjQyEoYlza0DHdw2zBXQVPo1LYv9au+wqVD0QcF6bD+1nr/VeCrpOw4atgJ9TOxQxmKqNvxOKq+fooiE6+uG1zVNY1cCgYEA1VVlm9t990WCWmgMW/YqMoXakpuvrVcvWMKoEVHMSNsBxVUv73gGLCpLZO8vFEWfv5LrrucWHiL+QjKhZjcpOjLCr4oYenfDdd/HmbwWixphch7Ee5Zd34YMDspvkWH9YWKSDHQ1osdMkCczB84Vg8fsn5sjJP0OkDB4OCD1w0I=";

    private String AES_KEY = "11111111aaaaaaaa";
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
        alyunSopHix();
    }


    /**
     * 阿里云SopHix热修复
     */
    private void alyunSopHix() {
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(version)
                .setAesKey(AES_KEY)
                .setEnableDebug(true)
                .setSecretMetaData(null, appSerret, RSA_KEY)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Toast.makeText(App.this, "表明补丁加载成功", Toast.LENGTH_SHORT).show();
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            Toast.makeText(App.this, "表明新补丁生效需要重启", Toast.LENGTH_SHORT).show();
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
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
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    private void isUseAnnotation() {
        /**
         * 检查类是否有注解
         */
        if (AnnotationUse.class.isAnnotationPresent(MethodInfo.class)) {
            /**
             * 通过反射来对AnnotationUse进行检查是否有注解
             */
            MethodInfo methodInfo = AnnotationUse.class.getAnnotation(MethodInfo.class);
            Log.d("App", "methodInfo:" + methodInfo);
        }
    }

}
