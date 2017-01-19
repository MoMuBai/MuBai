package com.lzw.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 手机组件调用工具类
 * <p>
 * Created by lzw on 2016/12/14.
 */
public final class PhoneUtil {

        private static long lastClickTime;

        /**
         * Don't let anyone instantiate this class.
         */
        private PhoneUtil() {
                throw new Error("Do not need instantiate!");
        }


        /**
         * 调用系统发短信界面
         *
         * @param activity    Activity
         * @param phoneNumber 手机号码
         * @param smsContent  短信内容
         */
        public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
                if (phoneNumber == null || phoneNumber.length() < 4) {
                        return;
                }
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", smsContent);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(it);
        }


        /**
         * 判断是否为连击
         *
         * @return boolean
         */
        public static boolean isFastDoubleClick() {
                long time = System.currentTimeMillis();
                long timeD = time - lastClickTime;
                if (0 < timeD && timeD < 500) {
                        return true;
                }
                lastClickTime = time;
                return false;
        }

        /**
         * 获取手机型号
         *
         * @param context 上下文
         * @return String
         */
        public static String getMobileModel(Context context) {
                try {
                        String model = android.os.Build.MODEL; // 手机型号
                        return model;
                } catch (Exception e) {
                        return "未知";
                }
        }

        /**
         * 获取手机品牌
         *
         * @param context 上下文
         * @return String
         */
        public static String getMobileBrand(Context context) {
                try {
                        String brand = android.os.Build.BRAND; // android系统版本号
                        return brand;
                } catch (Exception e) {
                        return "未知";
                }
        }
}
