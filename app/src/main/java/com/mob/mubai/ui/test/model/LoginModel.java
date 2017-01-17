package com.mob.mubai.ui.test.model;


import com.mob.mubai.base.service.MyFactory;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.ui.login.LoginContract;

import rx.Observable;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/25
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
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
