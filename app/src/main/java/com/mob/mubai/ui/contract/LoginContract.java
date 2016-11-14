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
        String getData();
    }
    interface View extends BaseView{
        void showInfo(String data);
    }

    abstract class Presenter extends BasePresenter<Model,View>{

        public abstract void setData();

        @Override
        protected void onStart() {

        }
    }
}
