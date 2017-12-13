package com.lzw.library.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzw.library.volley.GsonRequest;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/19
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class VolleyImpl implements NetModeInterface {
        private RequestQueue queue;

        public VolleyImpl(Context context) {
                queue = getQueue(context);
        }

        private RequestQueue getQueue(Context context) {
                if (queue == null) {
                        queue = Volley.newRequestQueue(context);
                }
                return queue;
        }

        @Override
        public void startRequest(String url, final NetInterface<String> request) {
                StringRequest strRequest = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                request.onSuccess(response);
                        }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                                request.onError();
                        }
                });
                queue.add(strRequest);
        }

        @Override
        public <T> void startRequest(String url, Class<T> clazz, final NetInterface<T> request) {
                GsonRequest<T> gsonRequest = new GsonRequest<T>(url, clazz, new Response.Listener<T>() {
                        @Override
                        public void onResponse(T response) {
                                request.onSuccess(response);
                        }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                                request.onError();
                        }
                });
                queue.add(gsonRequest);
        }
}
