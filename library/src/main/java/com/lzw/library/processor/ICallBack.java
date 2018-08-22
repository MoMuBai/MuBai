package com.lzw.library.processor;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is ICallBack
 */

public interface ICallBack {
    void onSuccess(String result);

    void onFail(String e);
}
