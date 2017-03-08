package com.mb.mubai.base.api;

/**
 * Created by lzw on 2016/12/17.
 */

public class MyFactory {

        private static MyService myService;

        private static Object object = new Object();

        public static MyService getMyService() {
                if (null == myService) {
                        synchronized (object) {
                                if (null == myService) {
                                        myService = new MyClient().getMyService();
                                }
                        }
                }
                return myService;
        }
}
