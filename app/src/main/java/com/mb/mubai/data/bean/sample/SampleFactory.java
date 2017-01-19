package com.mb.mubai.data.bean.sample;

/**
 * Created by lzw on 2016/12/20.
 */

public class SampleFactory {
        public static ISample create(int which) {
                if (which == 1) {
                        return new SampleA();
                } else {
                        return new SampleB();
                }
        }
}
