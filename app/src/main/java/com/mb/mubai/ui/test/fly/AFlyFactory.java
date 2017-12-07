package com.mb.mubai.ui.test.fly;

/**
 * @author: lzw
 * @date: 2017/12/7 下午4:34
 * @desc:
 */

public class AFlyFactory implements FlyFactoryProduce {

    @Override
    public FlyInterface produce() {
        return new AFly();
    }
}
