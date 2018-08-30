package com.mb.mubai.dn.andfix.ok;

import com.mb.mubai.dn.andfix.ReplaceClass;

/**
 * Author: lzw
 * Date: 2018/8/23
 * Description: This is Calculator
 * <p>
 * 服务端进行编程
 * 修复好的class文件
 */

public class Calculator {

    /**
     * 通过注解指明需要替换的包以及方法
     *
     * @return
     */
    @ReplaceClass(clazz = "com.mb.mubai.dn.andfix", method = "calculator")
    public int calculator() {
        int i = 1;//除数不为0
        int j = 100;
        return j / i;
    }
}
