package com.mb.mubai.dn.http;

import java.io.InputStream;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: 处理响应的顶层接口
 */

public interface IHttpListener {
    //接收上一个接口传入的数据
    void onSuccess(InputStream inputStream);

    void onFailure();
}
