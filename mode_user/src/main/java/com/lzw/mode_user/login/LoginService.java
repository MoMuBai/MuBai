package com.lzw.mode_user.login;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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


    @Override
    public Fragment newLoginFragment(Activity activity, int contrinerId, FragmentManager fragmentManager, Bundle bundle, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment loginFragment = new LoginFragment();
        transaction.add(contrinerId, loginFragment, tag);
        transaction.commit();
        return loginFragment;
    }
}
