package com.mob.mubai.ui.contract;

import android.view.View;

import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;

/**
 * Created by mubai on 2016/11/7.
 */

public interface LoginContract {

    interface Model extends BaseModel{
    }
    interface View extends BaseView{
        void showInfo();
    }

    abstract class Presenter extends BasePresenter<Model,View>{
        @Override
        public void onStart() {

        }
    }
}
