package com.lzw.library.dn;

/**
 * Author: lzw
 * Date: 2018/9/13
 * Description: This is ProcessManager
 */

public class ProcessManager {
    private static final ProcessManager ourInstance = new ProcessManager();

    public static ProcessManager getInstance() {
        return ourInstance;
    }

    private ProcessManager() {
    }


    /**
     * 进程A的注册，缓存一些方法
     *
     * @param clazz
     */
    public void register(Class<?> clazz) {

    }


    //UserManager 注册


}
