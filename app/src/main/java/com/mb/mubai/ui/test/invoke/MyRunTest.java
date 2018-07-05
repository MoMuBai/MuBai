package com.mb.mubai.ui.test.invoke;

import java.lang.reflect.Proxy;

/**
 * Author: lzw
 * Date: 2018/5/7
 * Description: This is MyRunTest
 */

public class MyRunTest {
    public static void main(String args[]) {
        MyTest myTest = new MyRun();

        MyInvoke myInvoke = new MyInvoke(myTest);

        MyTest myRun1 = (MyRun) Proxy.newProxyInstance(myInvoke.getClass().getClassLoader(), myInvoke.getClass().getInterfaces(), myInvoke);

        myRun1.run();

    }
}
