package com.mob.mubai.ui.contract;

import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;

import rx.Observable;

/**
 * Created by lzw on 2016/11/25.
 */

public interface LoginContract {
        interface Model extends BaseModel {
                String getData();

                Observable<DataResult<LoginValue>> login(String name, String pass);
        }

        interface View extends BaseView {
                void showData(String data);

                void login(LoginValue info);
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void setData();

                public abstract void login(String name, String pass);

                @Override
                protected void onStart() {

                }
        }
}
