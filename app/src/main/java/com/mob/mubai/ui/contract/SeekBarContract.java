package com.mob.mubai.ui.contract;

import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;

import rx.Observable;

/**
 *
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
 *
 */
public interface SeekBarContract {
        interface Model extends BaseModel {
                String getShow();
        }

        interface View extends BaseView {
                void show(String str);
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void myPost();

                @Override
                protected void onStart() {

                }
        }
}
