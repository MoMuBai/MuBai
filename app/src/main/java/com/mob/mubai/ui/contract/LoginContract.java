package com.mob.mubai.ui.contract;

import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;

/**
 * Created by mubai on 2016/11/7.
 */

public interface LoginContract {


    interface View extends BaseView{
        void showInfo();
    }

    interface Presenter extends BasePresenter{

    }
}
