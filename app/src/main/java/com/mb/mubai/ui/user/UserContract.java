package com.mb.mubai.ui.user;

import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.base.BaseView;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public interface UserContract {

    interface Model extends BaseModel {
        Observable<String> getUserInfo(String userId);
    }

    interface View extends BaseView {
        void showUserInfo(String data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getUserInfo(String userId);
    }
}
