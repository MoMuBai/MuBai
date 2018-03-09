package com.lzw.mvp.base;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is BasePresenter
 */

public class BasePresenter<V extends BaseView> {

    protected V mView;

    /**
     * 绑定View
     *
     * @param mView
     */
    public void attachView(V mView) {
        this.mView = mView;
    }

    /**
     * 在Activity销毁的时候解除绑定View
     */
    public void detachView() {
        this.mView = null;
    }

    /**
     * 判断Activity是否与View绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }
}
