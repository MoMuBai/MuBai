package com.mb.mubai.ui.home;

import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.base.BaseView;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public interface HomeContract {

    interface Model extends BaseModel {
        Observable<String> getData(String userId);
    }

    interface View extends BaseView {
        void showData(String data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getData(String userId);
    }
}
