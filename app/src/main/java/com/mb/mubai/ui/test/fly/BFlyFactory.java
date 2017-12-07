package com.mb.mubai.ui.test.fly;

/**
 * @author: lzw
 * @date: 2017/12/7 下午4:35
 * @desc:
 */

public class BFlyFactory implements FlyFactoryProduce {

    @Override
    public FlyInterface produce() {
        return new BFly();
    }
}
