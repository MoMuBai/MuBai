package com.mb.mubai.base.api.found;

/**
 * Created by lzw on 2017/3/13.
 */

public class FoundFactory {

    private static FoundService foundService;

    private static Object object = new Object();

    public static FoundService getFoundService() {
        if (null == foundService) {
            synchronized (object) {
                if (null == foundService) {
                    foundService = new FoundClient().getHomeService();
                }
            }
        }
        return foundService;
    }
}
