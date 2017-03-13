package com.mb.mubai.ui.home;

import com.mb.mubai.base.api.SchedulersCompat;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzw on 2017/3/13.
 */

public class HomePresenter extends HomeContract.Presenter {
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
