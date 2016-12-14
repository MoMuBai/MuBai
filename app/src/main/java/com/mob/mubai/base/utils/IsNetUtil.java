package com.mob.mubai.base.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lzw on 2016/11/8.
 */

public class IsNetUtil {

        private IsNetUtil() {
        /* cannot be instantiated */
                throw new UnsupportedOperationException("cannot be instantiated");
        }

        /**
         * 判断网址是否有效
         */
        public static boolean isLinkAvailable(String link) {
                Pattern pattern = Pattern.compile("^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(link);
                if (matcher.matches()) {
                        return true;
                }
                return false;
        }


        /**
         * 检测网络是否连接
         */
        public static boolean isNetConnected(Context context) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm != null) {
                        NetworkInfo[] infos = cm.getAllNetworkInfo();
                        if (infos != null) {
                                for (NetworkInfo ni : infos) {
                                        if (ni.isConnected()) {
                                                return true;
                                        }
                                }
                        }
                }
                return false;
        }

        /**
         * 检测wifi是否连接
         */
        public static boolean isWifiConnected(Context context) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm != null) {
                        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                                return true;
                        }
                }
                return false;
        }

        /**
         * 检测3G是否连接
         */
        public static boolean is3gConnected(Context context) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm != null) {
                        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                                return true;
                        }
                }
                return false;
        }

        /**
         * 是否有网络
         */
        public static boolean isNet(Context context) {
                if (isNetConnected(context) || isWifiConnected(context) || is3gConnected(context)) {
                        return true;
                } else {
                        return false;
                }
        }


        /**
         * 打开网络设置界面
         */
        public static void openSetting(Activity activity) {
                Intent intent = new Intent("/");
                ComponentName cm = new ComponentName("com.android.settings",
                          "com.android.settings.WirelessSettings");
                intent.setComponent(cm);
                intent.setAction("android.intent.action.VIEW");
                activity.startActivityForResult(intent, 0);
        }
}
