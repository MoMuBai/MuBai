package com.lzw.library.processor;

import android.app.Application;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is XUtilProcessor
 */

public class XUtilProcessor implements IHttpProcessor {


    public XUtilProcessor(Application application) {
        x.Ext.init(application);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        RequestParams requestParams = new RequestParams(url);
        if (null != params && !params.isEmpty()) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                requestParams.addQueryStringParameter(entry.getKey(), (String) entry.getValue());
            }
        }
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFail(ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
