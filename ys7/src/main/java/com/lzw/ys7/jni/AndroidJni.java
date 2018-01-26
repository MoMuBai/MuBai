package com.lzw.ys7.jni;

/**
 * @author: lzw
 * @date: 2018/1/9 上午10:42
 * @desc:
 */

public class AndroidJni {
    /**
     * 要与C层交互的方法
     *
     * @return
     */
    public static native String getString();

    /**
     * 加载so，要跟build.gradle中自己定义so名称保持一致
     */
    static {
        System.loadLibrary("MuBaiYs7");
    }
}
