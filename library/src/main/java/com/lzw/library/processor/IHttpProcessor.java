package com.lzw.library.processor;

import java.util.Map;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is IHttpProcessor
 */

public interface IHttpProcessor {
    void post(String url, Map<String, Object> params, ICallBack callBack);
}
