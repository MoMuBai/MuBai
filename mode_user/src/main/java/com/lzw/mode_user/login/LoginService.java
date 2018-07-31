package com.lzw.mode_user.login;

import com.zje.iot.model_component.service.AccountService;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is LoginService
 */

public class LoginService implements AccountService {
    @Override
    public boolean isLogin() {
        return new LoginModel().isLogin();
    }

    @Override
    public String getAccountId() {
        return new LoginModel().getAccountId() == null ? "123" : "321";
    }
}
