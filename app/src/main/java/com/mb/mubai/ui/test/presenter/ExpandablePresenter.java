package com.mb.mubai.ui.test.presenter;

import com.mb.mubai.ui.test.contract.ExpandableContract;

/**
 * @author: lzw
 * //
 * @date: 2017/3/22 上午9:50
 * //
 * @desc:
 */

public class ExpandablePresenter extends ExpandableContract.Presenter {
    @Override
    public void getData() {
        mView.showData(mModel.getData());
    }
}
