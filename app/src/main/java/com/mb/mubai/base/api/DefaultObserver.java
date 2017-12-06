package com.mb.mubai.base.api;


import com.mb.mubai.data.DataResult;

import rx.Observer;

/**
 * @author: lzw
 * @date: 2017/12/6 上午11:32
 * @desc:
 */

public class DefaultObserver<T extends DataResult> implements Observer<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T result) {
    }
}
