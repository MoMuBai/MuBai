package com.lzw.mvp.base;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is BaseCallBack
 */

public interface BaseCallBack<T> {
    void onSuccess(T msg);

    void onFailure(String msg);
}
