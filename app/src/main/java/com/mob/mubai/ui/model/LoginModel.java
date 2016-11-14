package com.mob.mubai.ui.model;

import com.mob.mubai.ui.contract.LoginContract;

/**
 * Created by mubai on 2016/11/11.
 */

public class LoginModel implements LoginContract.Model{
    @Override
    public String getData() {
        return "MuBai";
    }
}
