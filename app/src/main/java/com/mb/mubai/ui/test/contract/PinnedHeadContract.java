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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/25
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

public interface PinnedHeadContract {
        interface Model extends BaseModel {
                String[] getHeadData();

                String[][] getData();

                String[] getIndex();
        }

        interface View extends BaseView {

                void showData(String[] headData,String[][] data);

                void showIndex(String[] index);
        }

        abstract class Presenter extends BasePresenter<Model, View> {

                public abstract void getData();

                @Override
                protected void onStart() {

                }
        }
}
