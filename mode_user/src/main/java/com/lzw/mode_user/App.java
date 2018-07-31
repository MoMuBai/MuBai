package com.lzw.mode_user;

import android.app.Application;

import com.lzw.mode_user.login.LoginService;
import com.zje.iot.model_component.ServiceFactory;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is App
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 将LoginService注册到
         */
        ServiceFactory.getInstance().setAccountService(new LoginService());
    }
}
