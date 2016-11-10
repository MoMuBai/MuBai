package com.mob.mubai.base;

import android.content.Context;

/**
 * Created by mubai on 2016/11/3.
 */

public abstract class BasePresenter<M,T> {
    public Context context;
    public M mModel;
    public T mView;

    public void setVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
    }

}
