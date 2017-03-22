package com.mb.mubai.ui.test.contract;

import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.base.BaseView;

import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/3/22 上午9:47
 * //
 * @desc:
 */

public interface ExpandableContract {

    interface Model extends BaseModel {
        List<String> getData();
    }

    interface View extends BaseView {
        void showData(List<String> data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getData();


        @Override
        protected void onStart() {

        }
    }
}
