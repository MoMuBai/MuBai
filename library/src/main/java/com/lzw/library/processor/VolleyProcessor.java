package com.lzw.library.processor;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is VolleyProcessor
 */

public class VolleyProcessor implements IHttpProcessor {

    private RequestQueue requestQueue = null;

    public VolleyProcessor(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }


    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFail(error.toString());
            }
        });
        requestQueue.add(request);
    }
}
