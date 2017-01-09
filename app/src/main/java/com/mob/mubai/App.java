package com.mob.mubai;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.mob.mubai.base.exception.LocalFileHandler;
import com.mob.mubai.base.utils.L;
import com.mob.mubai.base.utils.OkHttpClientUtil;
import com.mob.mubai.base.utils.SpUtils;

import java.io.IOException;

/**
 *
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
 *
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
                strictMode();
                SpUtils.init(this);
                try {
                        OkHttpClientUtil.getInstance()
                                  .setCertificates(getAssets().open("srca.cer"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                //配置程序异常退出处理
//        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
        }

        /*
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
