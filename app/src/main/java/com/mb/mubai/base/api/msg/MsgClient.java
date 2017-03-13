package com.mb.mubai.base.api.msg;

import com.mb.mubai.base.network.GsonUtil;
import com.mb.mubai.base.network.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/9
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
public class MsgClient {
        private static MsgService msgService;

        private static Retrofit retrofit;

        public MsgClient() {
                retrofit = new Retrofit.Builder()
                          .baseUrl("url")
                          .client(OkHttpManager.getOkHttpClient())
                          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                          .addConverterFactory(ScalarsConverterFactory.create())
                          .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                          .build();
                if (null == msgService) {
                        msgService = retrofit.create(MsgService.class);
                }
        }

        public MsgService getApiService() {
                return msgService;
        }
}
