package com.mb.mubai.dn.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.InputStream;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: This is JsonHttpListener
 */

public class JsonHttpListener<M> implements IHttpListener {

    Class<M> reponseClass;

    private IDataListener<M> dataListener;

    //用于线程切换
    Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<M> reponseClass, IDataListener<M> dataListener) {
        this.reponseClass = reponseClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        //inputStream已经拿到数据
        //1.把inputStream中的数据转换成json
        String content = getContent(inputStream);
        //2.把String转换成用户需要的对象类型
        M response = JSON.parseObject(content, reponseClass);
        //3.在把结果传给用户

        handler.post(new Runnable() {
            @Override
            public void run() {
                dataListener.onSuccess(response);
            }
        });
    }

    /**
     * 将inputStream中的数据转换成json
     *
     * @param inputStream
     * @return
     */
    private String getContent(InputStream inputStream) {
        return null;
    }

    @Override
    public void onFailure() {

    }
}
