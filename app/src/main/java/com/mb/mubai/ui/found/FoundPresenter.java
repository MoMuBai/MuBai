package com.mb.mubai.ui.found;

import com.mb.mubai.base.api.SchedulersCompat;
import com.mb.mubai.ui.home.HomeContract;

/**
 * Created by lzw on 2017/3/13.
 */

public class FoundPresenter extends FoundContract.Presenter {
    @Override
    protected void onStart() {

    }

    @Override
    void getData(String userId) {
        mRxManager.add(mModel.getData(userId)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(s -> {
                    mView.showData(s);
                }));
    }
}
