package com.mb.mubai.dn.http;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: This is IDataListener
 */

public interface IDataListener<M> {
    void onSuccess(M m);

    void onFailure(String s);
}
