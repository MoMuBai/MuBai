package com.mb.mubai.ui.test.presenter;

import com.mb.mubai.ui.test.contract.DownListContract;

/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午4:17
 * //
 * @desc:
 */

public class DownListPresenter extends DownListContract.Presenter {
    @Override
    public void getData(String type) {
        mView.showData(mModel.getData(type));
        mView.showMainData(mModel.getMainData());
    }
}
