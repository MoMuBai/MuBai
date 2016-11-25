package com.mob.mubai.ui.model;

import com.mob.mubai.ui.contract.LoginContract;

/**
 * Created by lzw on 2016/11/25.
 */

public class LoginModel implements LoginContract.Model {
        @Override
        public String getData() {
                return "MuBai";
        }
}
