package com.mb.mubai.base.api.home;

/**
 * Created by lzw on 2017/3/13.
 */

public class HomeFactory {

    private static HomeService homeService;

    private static Object object = new Object();

    public static HomeService getHomeService() {
        if (null == homeService) {
            synchronized (object) {
                if (null == homeService) {
                    homeService = new HomeClient().getHomeService();
                }
            }
        }
        return homeService;
    }
}
