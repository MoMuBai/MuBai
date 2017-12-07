package com.mb.mubai.ui.test.run;

/**
 * @author: lzw
 * @date: 2017/12/7 下午3:23
 * @desc: 静态工厂模式
 */

public class RunStaticFactory {

    public static RunInterface runA() {
        return new ARuning();
    }

    public static RunInterface runB() {
        return new BRuning();
    }
}
