package com.lzw.library.network;

import com.google.gson.Gson;
import com.lzw.library.utils.OkHttpClientUtil;
import com.squareup.okhttp.Request;

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
public class OkHttpImpl implements NetModeInterface {
        public OkHttpImpl() {
        }

        @Override
        public void startRequest(String url, final NetInterface<String> requestNet) {
                OkHttpClientUtil.getAsyn(url, new OkHttpClientUtil.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                                requestNet.onError();
                        }

                        @Override
                        public void onResponse(String response) {
                                requestNet.onSuccess(response);

                        }
                });
        }

        @Override
        public <T> void startRequest(String url, final Class<T> clazz, final NetInterface<T> requestNet) {
                final Gson gson = new Gson();
                OkHttpClientUtil.getAsyn(url, new OkHttpClientUtil.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                                requestNet.onError();
                        }

                        @Override
                        public void onResponse(String response) {
                                T t = gson.fromJson(response, clazz);
                                requestNet.onSuccess(t);
                        }
                });
        }
}
