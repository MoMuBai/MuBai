package com.mob.mubai.ui.presenter;

import com.mob.mubai.ui.contract.LoginContract;
import com.mob.mubai.data.model.PasswordValidator;
import com.mob.mubai.base.utils.UserManager;

/**
 * Created by mubai on 2016/11/3.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private UserManager mUserManager;
    private PasswordValidator mPasswordValidator;

    public LoginPresenter(UserManager mUserManager,PasswordValidator mPasswordValidator) {
        this.mUserManager = mUserManager;
        this.mPasswordValidator = mPasswordValidator;
    }

    public void login(String name, String pass){
        if (name == null || name.length() == 0) return;
        if (mPasswordValidator.verifyPassword(pass)) return;  //<==
        mUserManager.performLogin(name,pass);
    }
}
