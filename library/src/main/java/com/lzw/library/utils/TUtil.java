package com.lzw.library.utils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by lzw on 2016/11/10.
 */
public class TUtil {

    public static <T> T getT(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i]).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
