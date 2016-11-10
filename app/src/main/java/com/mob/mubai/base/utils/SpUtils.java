package com.mob.mubai.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mubai on 2016/11/8.
 * SP读取方法
 */
public final class SpUtils {

    public static String PREFERENCE_NAME = "MuBai";

    static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
    }


    /**
     * 存入String类型参数进入SharePreferences
     *
     * @param context
     * @param key
     * @param value
     * @return 是否存入成功
     */
    public static boolean putString(Context context, String key, String value) {
        return prefs.edit().putString(key, value).commit();
    }

    /**
     * 取出SharePreferences的String类型值，没有设置默认值
     *
     * @param context
     * @param key
     * @return 得到共享参数的存入key的String类型值
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 取出SharePreferences的String类型值，设置默认值(取不到该key值，默认值)
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return 得到共享参数的存入key的String类型值
     */
    public static String getString(Context context, String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }


    public static boolean putInt(Context context, String key, int value) {
        return prefs.edit().putInt(key, value).commit();
    }


    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }


    public static int getInt(Context context, String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }


    public static boolean putLong(Context context, String key, long value) {
        return prefs.edit().putLong(key, value).commit();
    }


    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }


    public static long getLong(Context context, String key, long defaultValue) {
        return prefs.getLong(key, defaultValue);
    }


    public static boolean putFloat(Context context, String key, float value) {
        return prefs.edit().putFloat(key, value).commit();
    }


    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }


    public static float getFloat(Context context, String key, float defaultValue) {
        return prefs.getFloat(key, defaultValue);
    }


    public static boolean putBoolean(Context context, String key, boolean value) {
        return prefs.edit().putBoolean(key, value).commit();
    }


    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }


    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }


    /**
     * 清空所有共享参数
     *
     * @param context
     * @return 是否清空共享参数
     */
    public static boolean clearAllPrefer(Context context) {
        return prefs.edit().clear().commit();
    }
}