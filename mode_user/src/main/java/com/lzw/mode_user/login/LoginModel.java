package com.lzw.mode_user.login;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is LoginModel
 */

public class LoginModel {

    private boolean isLogin;
    private String accountId;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
