package com.mb.mubai.dn.http;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: This is HttpTask
 */

public class HttpTask<T> implements Runnable {

    private IHttpListener iHttpListener;

    private IHttpService iHttpService;

    public HttpTask(String url, T requestParams, IHttpService iHttpService, IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
        this.iHttpService = iHttpService;
        this.iHttpService.setUrl(url);
        this.iHttpService.setHttpCallBack(iHttpListener);
        //需要把请求参数转换成json字符串
        String requestContent = JSON.toJSONString(requestParams);
        //在把字符串变数组
        try {
            this.iHttpService.setRequestParams(requestContent.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        iHttpService.execute();
    }
}
