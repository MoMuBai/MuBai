package com.mb.mubai.base.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lzw
 * @date: 2017/11/14 下午11:17
 * @desc:
 */


//是否会保存到JavaDoc中
@Documented
//保留时间，source:源码时，class：编译时，runtime：运行时，默认为class，值为source大多为mark annotation，如@overrided等
@Retention(RetentionPolicy.RUNTIME)
//表示可以用来修饰哪些程序元素，如方法，变量，类，参数等等，如果没有标注则表示修饰所有
@Target(ElementType.METHOD)
//是否可以被继承，默认为false
@Inherited
public @interface MethodInfo {
    String author() default "quickly520@126.com";

    String date();

    String Desc() default "自定义Annotation";
}
