package com.mb.mubai.ui.test.run;

/**
 * @author: lzw
 * @date: 2017/12/7 下午3:06
 * @desc: 普通工厂模式
 */

public class RunFactory {

    public RunInterface runType(String type) {
        if ("A".endsWith(type)) {
            return new ARuning();
        } else if ("B".endsWith(type)) {
            return new BRuning();
        } else {
            return null;
        }
    }
}
