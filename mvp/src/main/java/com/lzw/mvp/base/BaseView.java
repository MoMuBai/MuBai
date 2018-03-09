package com.lzw.mvp.base;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is BaseView
 */

public interface BaseView {
    void showLoad();

    void showData(String msg);

    void showError(String msg);
}
