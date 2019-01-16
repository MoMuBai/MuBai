package com.lzw.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/18
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  SP读取方法
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public final class SpUtils {
    private static SharedPreferences mSharedPreferences;

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
        }
    }

    /**
     * 添加Int类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedIntData(Context context, String key, int value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 获取Int类型
     *
     * @param context
     * @param key
     * @return
     */
    public static int getSharedIntData(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getInt(key, -1);
    }

    /**
     * 添加Long类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedlongData(Context context, String key, long value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * 获取Long类型
     *
     * @param context
     * @param key
     * @return
     */
    public static long getSharedlongData(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getLong(key, -1l);
    }

    /**
     * 添加Float类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedFloatData(Context context, String key,
                                          float value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * 获取Float类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Float getSharedFloatData(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getFloat(key, -1f);
    }

    /**
     * 添加Boolean类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedBooleanData(Context context, String key,
                                            boolean value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取Boolean类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Boolean getSharedBooleanData(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getBoolean(key, true);
    }

    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setSharedStringData(Context context, String key,
                                           String value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @return
     */
    public static String getSharedStringData(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getString(key, null);
    }


    /**
     * 清空所有共享参数
     *
     * @param context
     * @return 是否清空共享参数
     */
    public static boolean clearAllPrefer(Context context) {
        return mSharedPreferences.edit().clear().commit();
    }
}