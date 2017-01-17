package com.mob.mubai.ui.login;


import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/21
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

public interface LoginContract {
        interface Model extends BaseModel {
                String getData();

                Observable<DataResult<LoginValue>> login(String name, String pass);

                LoginValue getLoginValue(String name, String pass);

        }

        interface View extends BaseView {
                void showData(String data);

                void login(LoginValue info);

                void showJackson();
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void setData();

                public abstract void login(String name, String pass);

                public abstract void getJsonFromObject(String name, String pass);

                public abstract void getObjectFromJson();

                @Override
                protected void onStart() {

                }
        }
}
