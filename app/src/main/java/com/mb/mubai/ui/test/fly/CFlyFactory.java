package com.mb.mubai.ui.test.fly;

/**
 * @author: lzw
 * @date: 2017/12/7 下午4:46
 * @desc:
 */

public class CFlyFactory implements FlyFactoryProduce {
    @Override
    public FlyInterface produce() {
        return new CFly();
    }
}
