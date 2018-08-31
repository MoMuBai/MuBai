package com.mb.mubai.dn.http;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: This is MNet
 */

public class MNet {
    public static <T, M> void sendJsonRequest(String url, T requestInfo, Class<M> response, IDataListener dataListener) {
        IHttpService httpService = new JsonHttpService();
        IHttpListener httpListener = new JsonHttpListener<>(response, dataListener);
        HttpTask httpTask = new HttpTask(url, requestInfo, httpService, httpListener);
        ThreadPoolManager.getInstance().execute(httpTask);
    }
}
