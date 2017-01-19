package com.mb.mubai.ui.test.contract;


import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.base.BaseView;

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
public interface SeekBarContract {
        interface Model extends BaseModel {
                String getShow();
        }

        interface View extends BaseView {
                void show(String str);

                void show();
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void myPost();


                public abstract void start();

                @Override
                protected void onStart() {

                }
        }
}
