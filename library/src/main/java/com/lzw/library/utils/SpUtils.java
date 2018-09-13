package com.lzw.library.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

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
    private static SharedPreferences.Editor editor;

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(context);
            editor = mSharedPreferences.edit();
        }
    }

    /**
     * 存储String
     */
    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取保存的数据String
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获取保存的数据String
     */
    public static String getString(String key, String value) {
        return mSharedPreferences.getString(key, value);
    }


    /**
     * 存储Int
     */
    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取保存的数据Int
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * 获取保存的数据Int
     */
    public static int getInt(String key, int value) {
        return mSharedPreferences.getInt(key, value);
    }


    /**
     * 存储Float
     */
    public static void putFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 获取保存的数据Float
     */
    public static float getFloat(String key) {
        return getFloat(key, -1f);
    }

    /**
     * 获取保存的数据Float
     */
    public static float getFloat(String key, float value) {
        return mSharedPreferences.getFloat(key, value);
    }

    /**
     * 存储Boolean
     */
    public static void putBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取保存的数据Boolean
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取保存的数据Boolean
     */
    public static boolean getBoolean(String key, boolean value) {
        return mSharedPreferences.getBoolean(key, value);
    }


    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return mSharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }
}