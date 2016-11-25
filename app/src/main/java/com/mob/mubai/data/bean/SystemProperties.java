package com.mob.mubai.data.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lzw on 2016/11/11.
 * 反射出系统的方法
 */

public class SystemProperties {
    public static String get(String key){
        String value = "";
        Class<?> cl = null;
        try {
            cl = Class.forName("android.os.SystemProperties");
            Method hideMethod = cl.getMethod("get",String.class);
            Object o = cl.newInstance();
            value = (String) hideMethod.invoke(o,key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }
}
