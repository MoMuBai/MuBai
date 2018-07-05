package com.mb.mubai.ui.test.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: lzw
 * Date: 2018/5/7
 * Description: This is MyInvoke
 */

public class MyInvoke implements InvocationHandler {

    private Object object;

    public MyInvoke(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Before:" + method);
        method.invoke(object, objects);
        System.out.println("After:" + method);
        System.out.println(method);
        return null;
    }

}
