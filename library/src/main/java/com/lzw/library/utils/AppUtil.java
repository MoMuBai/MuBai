package com.lzw.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.WindowManager;


import java.util.UUID;


/**
 * Created by lzw on 2016/11/10.
 * App常用的方法
 */
public final class AppUtil {

        private static Float version = 1.0f;

        private AppUtil() {
        /* cannot be instantiated*/
                throw new UnsupportedOperationException("cannot be instantiated");
        }

        /**
         * 使状态栏透明
         *
         * @param activity
         */
        public static void setTranslucent(Activity activity) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        // 设置状态栏透明
                        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        // 设置根布局的参数
                        ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
                        rootView.setFitsSystemWindows(true);
                        rootView.setClipToPadding(true);
                }
        }

        /**
         * 检查是否版本更新
         */
        public static boolean checkIsUpdate(Context context) {
                //TODO 需要进行网络请求获取版本号
                try {
                        if (version > Float.parseFloat(AppUtil.getVersionName(context))) {
                                return true;
                        } else {
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return false;
        }

        /**
         * 获取应用程序名称
         *
         * @param context
         * @return
         */
        public static String getAppName(Context context) {

                PackageManager packageManager = context.getPackageManager();
                try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                        int labelRes = packageInfo.applicationInfo.labelRes;
                        return context.getResources().getString(labelRes);
                } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 获取应用程序版本名称信息
         *
         * @param context
         * @return 当前应用的版本名称
         */
        public static String getVersionName(Context context) {
                try {
                        PackageManager packageManager = context.getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageInfo(
                                  context.getPackageName(), 0);
                        return packageInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 获取应用程序的版本Code信息
         *
         * @param context
         * @return 版本code
         */
        public static int getVersionCode(Context context) {
                try {
                        PackageManager packageManager = context.getPackageManager();
                        PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                        return packageInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                }
                return 0;
        }

        /**
         * 获取唯一设备号
         */
        public static String getDeviceId(Context context) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                final String tmDevice, tmSerial, tmPhone, androidId;
                tmDevice = "" + tm.getDeviceId();
                tmSerial = "" + tm.getSimSerialNumber();
                androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
                String uniqueId = deviceUuid.toString();
                return uniqueId;
        }


}
