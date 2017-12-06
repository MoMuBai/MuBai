package com.mb.mubai.base.api.user;

/**
 * Created by lzw on 2016/12/17.
 */

public class UserFactory {

    private static UserService userService;

    private static Object object = new Object();

    public static UserService getUserService() {
        if (null == userService) {
            synchronized (object) {
                if (null == userService) {
                    userService = new UserClient().getMyService();
                }
            }
        }
        return userService;
    }
}
