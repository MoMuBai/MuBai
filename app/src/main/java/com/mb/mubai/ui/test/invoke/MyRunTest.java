package com.mb.mubai.ui.test.invoke;

import java.lang.reflect.Proxy;

/**
 * Author: lzw
 * Date: 2018/5/7
 * Description: This is MyRunTest
 */

public class MyRunTest {
    public static void main(String args[]) {
        MyRun myRun = new MyRun();
        myRun.run();

        MyInvoke myInvoke = new MyInvoke(myRun);

        MyRun myRun1 = (MyRun) Proxy.newProxyInstance(myInvoke.getClass().getClassLoader(), myInvoke.getClass().getInterfaces(), myInvoke);

        myRun1.run();

    }
}
