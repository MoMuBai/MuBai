package com.mb.mubai.ui.test.presenter;


import com.lzw.library.utils.L;
import com.mb.mubai.base.api.ApiClient;
import com.mb.mubai.ui.test.contract.SeekBarContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/4
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
public class SeekBarPresenter extends SeekBarContract.Presenter {
        @Override
        public void myPost() {
                mView.show(mModel.getShow());
        }

        @Override
        public void start() {
                //OkHttp请求
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                Request request = new Request.Builder()
//                          .addHeader("version", "1.0.2")
//                          .addHeader("versioncode", "5")
                          .url("https://www.baidu.com/")
                          .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                L.d("Exception:" + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                L.d("response:" + request.body().toString());
                        }
                });
                //Retrofit请求
                ApiClient.getService().getStr("name");
        }
}
