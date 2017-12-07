package com.mb.mubai.ui.test.run;

/**
 * @author: lzw
 * @date: 2017/12/7 下午3:19
 * @desc: 多个工厂模式
 */

public class RunMoreFactory {
    public RunInterface runA() {
        return new ARuning();
    }

    public RunInterface runB() {
        return new BRuning();
    }
}
