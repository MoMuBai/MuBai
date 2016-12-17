package com.mob.mubai.ui.model;

import com.mob.mubai.base.service.MyFactory;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.ui.contract.LoginContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by lzw on 2016/11/25.
 */

public class LoginModel implements LoginContract.Model {
        @Override
        public String getData() {
                return "MuBai";
        }

        @Override
        public Observable<DataResult<LoginValue>> login(String name, String pass) {
                return MyFactory.getMyService().login(name, pass);
        }

        @Override
        public LoginValue getLoginValue(String name, String pass) {
                LoginValue loginValue = new LoginValue();
                loginValue.setId("0");
                loginValue.setName(name);
                return loginValue;
        }
}
