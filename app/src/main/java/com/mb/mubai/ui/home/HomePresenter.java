package com.mb.mubai.ui.home;

import com.mb.mubai.base.api.SchedulersCompat;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return mModel.getHome(s);
                    }
                })
                .subscribe(s -> {
                    mView.showData(s);
                }));
    }
}
