package com.mob.mubai.base.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.Stack;

/**
 * Created by lzw on 2016/11/8.
 */

public class AppManager {
        private static Stack<Activity> activityStack;
        private static Stack<Fragment> fragmentStack;
        private static AppManager instance;

        private AppManager() {
        }

        /**
         * 单一实例
         */
        public static AppManager getAppManager() {

                if (instance == null) {
                        instance = new AppManager();
                }
                return instance;
        }

        /**
         * 添加Activity到堆栈
         */
        public void addActivity(Activity activity) {
                if (activityStack == null) {
                        activityStack = new Stack<>();
                }
                activityStack.add(activity);
        }

        /**
         * 添加Fragment到堆栈
         */
        public void addFragment(Fragment fragment) {
                if (fragmentStack == null) {
                        fragmentStack = new Stack<>();
                }
                fragmentStack.add(fragment);
        }

        /**
         * 获取当前Activity（堆栈中最后一个压入的）
         */
        public Activity currentActivity() {
                Activity activity = activityStack.lastElement();
                return activity;
        }

        /**
         * 结束当前Activity（堆栈中最后一个压入的）
         */
        public void finishActivity() {
                Activity activity = activityStack.lastElement();
                finishActivity(activity);
        }


        /**
         * 结束指定的Activity
         */
        public void finishActivity(Activity activity) {
                if (activity != null) {
                        activityStack.remove(activity);
                        activity.finish();
                        activity = null;
                }
        }

        /**
         * 结束指定类名的Activity
         */
        public void finishActivity(Class<?> cls) {
                for (Activity activity : activityStack) {
                        if (activity.getClass().equals(cls)) {
                                finishActivity(activity);
                        }
                }
        }

        /**
         * 结束所有Activity
         */
        public void finishAllActivity() {
                for (int i = 0, size = activityStack.size(); i < size; i++) {
                        if (null != activityStack.get(i)) {
                                Activity activity = activityStack.get(i);
                                if (!activity.isFinishing()) {
                                        activity.finish();
                                }
                        }
                }
                activityStack.clear();
        }

        /**
         * 退出应用程序
         */
        public void AppExit(Context context) {
                try {
                        finishAllActivity();
            /*Intent intent = new Intent(context, MainActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
            		context, 0, intent,
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            //退出程序
            AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                    restartIntent); // 1秒钟后重启应用
*/
                        // 杀死该应用进程
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                } catch (Exception e) {
                }
        }

        /**
         * 移除Activity
         */
        public void removeActivity(Activity activity) {
                if (activity != null) {
                        L.i("--------Activity已移除栈中");

                        activityStack.remove(activity);
                }
        }

        /**
         * 移除Fragment
         */
        public void removeFragment(Fragment fragment) {
                if (fragment != null) {
                        fragmentStack.remove(fragment);
                }
        }
}
