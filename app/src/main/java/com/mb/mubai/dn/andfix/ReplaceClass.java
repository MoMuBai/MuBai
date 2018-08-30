package com.mb.mubai.dn.andfix;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: lzw
 * Date: 2018/8/23
 * Description: This is ReplaceClass
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReplaceClass {
    String clazz();//通过注解反射找到需要注解的class名

    String method();//通过注解反射找到需要注解的方法
}
