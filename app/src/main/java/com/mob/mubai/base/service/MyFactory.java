package com.mob.mubai.base.service;

/**
 * Created by lzw on 2016/12/17.
 */

public class MyFactory {

        private static MyService myService;

        public static MyService getMyService() {
                if (null == myService) {
                        synchronized (MyFactory.class) {
                                if (null == myService) {
                                        myService = new MyClient().getMyService();
                                }
                        }
                }
                return myService;
        }
}
