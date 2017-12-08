package com.mb.mubai.base.util;

/**
 * @author: lzw
 * @date: 2017/12/7 下午5:23
 * @desc:
 */

public class SingletonHelper {

    private static SingletonHelper ourInstance = null;

    /**
     * 加锁
     *
     * @return
     */
    public static SingletonHelper getInstance() {
        if (ourInstance == null) {
            synchronized (ourInstance) {
                if (ourInstance == null) {
                    ourInstance = new SingletonHelper();
                }
            }
        }
        return ourInstance;
    }

    private SingletonHelper() {
    }
}
