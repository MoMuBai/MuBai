package com.mob.mubai.base;

import android.content.Context;

/**
 * Created by lzw on 2016/11/3.
 */
public abstract class BasePresenter<M, V> {

        public M mModel;

        public V mView;

        public RxManager mRxManager = new RxManager();

        public void setVM(M m, V v) {
                this.mModel = m;
                this.mView = v;
                onStart();
        }

        protected abstract void onStart();


        public void onDestory() {
                mRxManager.clear();
        }
}
