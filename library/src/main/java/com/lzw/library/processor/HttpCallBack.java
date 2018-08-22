package com.lzw.library.processor;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is HttpCallBack
 */

public abstract class HttpCallBack<Result> implements ICallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> cls = asyncData();
        Result t = (Result) gson.fromJson(result, cls);
        onSuccess(t);
    }

    /**
     * 获取参数类型
     *
     * @return
     */
    public Class<?> asyncData() {
        Type type = this.getClass().getGenericSuperclass();
        Type[] resultType = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) resultType[0];
    }

    @Override
    public void onFail(String e) {

    }


    /**
     * 对外暴露的方法返回指定的Data
     *
     * @param result
     */
    public abstract void onSuccess(Result result);
}
