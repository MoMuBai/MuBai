package com.mb.mubai.base.api;


import com.mb.mubai.data.DataResult;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将InfoResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 *            <p>
 *            Created by lzw on 2016/12/16.
 */

public class DataResultFunc<T> implements Func1<DataResult<T>, T> {
    @Override
    public T call(DataResult<T> dataResult) {
        if (dataResult.getCode().endsWith("200")) {
            throw new ApiException(dataResult.getCode());
        }
        return dataResult.getData();
    }
}
