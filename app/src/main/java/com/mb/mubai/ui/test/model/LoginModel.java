package com.mb.mubai.ui.test.model;


import com.mb.mubai.base.service.MyFactory;
import com.mb.mubai.data.DataResult;
import com.mb.mubai.data.bean.LoginValue;
import com.mb.mubai.ui.login.LoginContract;

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
