package com.mb.mubai.base.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lzw
 * @date: 2017/11/15 上午9:15
 * @desc:
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.SOURCE)
@Documented
@Inherited
/**
 * 一个属性都没有的Annotation为MarkAnnotation
 */
public @interface ClassInfo {
}
