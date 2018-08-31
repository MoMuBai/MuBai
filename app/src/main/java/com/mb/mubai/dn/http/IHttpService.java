package com.mb.mubai.dn.http;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: 处理请求的顶层接口
 */

public interface IHttpService {
    void setUrl(String url);

    void setRequestParams(byte[] requestParams);

    /**
     * 用来执行网络请求
     */
    void execute();

    void setHttpCallBack(IHttpListener iHttpListener);
}
