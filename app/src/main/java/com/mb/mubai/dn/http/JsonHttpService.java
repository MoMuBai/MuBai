package com.mb.mubai.dn.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: 真实的网络操作
 */

public class JsonHttpService implements IHttpService {

    private String url;
    private byte[] requestData;
    private IHttpListener iHttpListener;
    private URLConnection conn;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestParams(byte[] requestParams) {
        this.requestData = requestParams;
    }

    @Override
    public void execute() {
        URL url = null;
        try {
            url = new URL(this.url);
            conn = url.openConnection();

            //2.处理设置参数和一般请求属性
            //2.1设置参数
            //可以根据请求的需要设置参数
            conn.setUseCaches(false);
            conn.setConnectTimeout(5000); //请求超时时间

            //2.2请求属性
            //设置通用的请求属性 更多的头字段信息可以查阅HTTP协议
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            //3.使用 connect 方法建立到远程对象的实际连接。
            conn.connect();

            //4.远程对象变为可用。远程对象的头字段和内容变为可访问。
            //4.1获取响应的头字段
            Map<String, List<String>> headers = conn.getHeaderFields();
            System.out.println(headers); //输出头字段

            //4.2获取响应正文
            BufferedReader reader = null;
            StringBuffer resultBuffer = new StringBuffer();
            String tempLine = null;

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHttpCallBack(IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
    }
}
