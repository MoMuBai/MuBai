package com.lzw.mvp;

import com.lzw.mvp.base.BasePresenter;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is MvpPresenter
 */

public class MvpPresenter extends BasePresenter<MvpView> {

    public MvpPresenter() {
    }

    public void getData(String params) {
        mView.showLoad();
        new MvpModel().setParams(params).execute(new MvpCallBack<String>() {
            @Override
            public void onSuccess(String msg) {
                mView.showData(msg);
            }

            @Override
            public void onFailure(String msg) {
                mView.showError(msg);
            }
        });
    }
}
