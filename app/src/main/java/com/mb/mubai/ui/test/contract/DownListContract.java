package com.mb.mubai.ui.test.contract;

import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.base.BaseView;

import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午4:15
 * //
 * @desc:
 */

public interface DownListContract {
    interface Model extends BaseModel {
        List<String> getData(String type);

        List<String> getMainData();

    }

    interface View extends BaseView {
        void showData(List<String> datas);

        void showMainData(List<String> datas);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getData(String type);


        @Override
        protected void onStart() {

        }
    }
}
