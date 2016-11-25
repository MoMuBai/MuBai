package com.mob.mubai.ui.contract;

import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;

/**
 * Created by lzw on 2016/11/25.
 */

public interface RecyclerContract {
        interface Model extends BaseModel {
        }

        interface View extends BaseView {
        }

        abstract class Presenter extends BasePresenter<Model, View> {


                @Override
                protected void onStart() {

                }
        }
}
