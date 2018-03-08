package com.mb.mubai.base.util;

/**
 * @author: lzw
 * @date: 2017/12/7 下午5:23
 * @desc:
 */

public class SingletonHelper {

    private static SingletonHelper ourInstance = null;

    /**
     * 饿汉
     */
    private static SingletonHelper inInstance = new SingletonHelper();

    private volatile static SingletonHelper singletonHelper;


    private SingletonHelper() {
    }


    /**
     * 双重加锁
     *
     * @return
     */
    public static SingletonHelper getSingletonHelper() {
        if (null == singletonHelper) {
            synchronized (SingletonHelper.class) {
                if (null == singletonHelper) {
                    singletonHelper = new SingletonHelper();
                }
            }
        }
        return singletonHelper;
    }

    /**
     * 饿汉，变种
     */
    static {
        inInstance = new SingletonHelper();
    }


    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final SingletonHelper SINGLETON_HELPER = new SingletonHelper();
    }


    public static final SingletonHelper getOurInstance() {
        return SingletonHolder.SINGLETON_HELPER;
    }

    public static SingletonHelper getInInstance() {
        return inInstance;
    }

    /**
     * 懒汉，线程安全
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

    //    /**
//     * 懒汉，线程不安全
//     *
//     * @return
//     */
//    public static SingletonHelper getInstance() {
//        if (ourInstance == null) {
//            ourInstance = new SingletonHelper();
//        }
//        return ourInstance;
//    }
//
//
}
