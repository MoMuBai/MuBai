package com.mob.mubai.ui.test.contract;


import com.mob.mubai.base.BaseModel;
import com.mob.mubai.base.BasePresenter;
import com.mob.mubai.base.BaseView;
import com.mob.mubai.data.bean.ViewTypeBean;

import java.util.List;

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
