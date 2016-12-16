package com.mob.mubai.ui.presenter;

import com.mob.mubai.base.helps.DataResultFunc;
import com.mob.mubai.base.utils.UserManager;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.data.model.PasswordValidator;
import com.mob.mubai.ui.contract.LoginContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzw on 2016/11/25.
 */

public class LoginPresenter extends LoginContract.Presenter {

        private UserManager userManager;
        private PasswordValidator passwordValidator;

        public LoginPresenter(UserManager userManager, PasswordValidator passwordValidator) {
                this.userManager = userManager;
                this.passwordValidator = passwordValidator;
        }

        @Override
        public void setData() {
                mView.showData(mModel.getData());
        }

        @Override
        public void login(String name, String pass) {
                mRxManager.add(mModel.login(name, pass)
                          .map(new DataResultFunc<LoginValue>())
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(loginValue -> {
                                  mView.login(loginValue);
                          }));
        }

        public void login_1(String name, String pass) {
                userManager.performLogin(name, pass);
        }

        public void login_2(String username, String password) {
                if (username == null || username.length() == 0) return;
                //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
                if (passwordValidator.verifyPassword(password)) return;//这边可能是个耗时操作

                userManager.performLogin(null, password);
        }
}
