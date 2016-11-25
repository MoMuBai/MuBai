package com.mob.mubai.ui.contract;

import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;

/**
 * Created by lzw on 2016/11/25.
 */

public interface LoginContract {
        interface Model extends BaseModel {
                String getData();
        }

        interface View extends BaseView {
                void showData(String data);
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void setData();

                @Override
                protected void onStart() {

                }
        }
}
