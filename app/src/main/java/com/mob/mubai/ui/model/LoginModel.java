package com.mob.mubai.ui.model;

import com.mob.mubai.base.utils.NetWorkUtils;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.ui.contract.LoginContract;

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
                return NetWorkUtils.getInstance().getApiService().login(name, pass);
        }
}
