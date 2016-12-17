package com.mob.mubai.base.service;

import com.mob.mubai.base.api.ApiService;
import com.mob.mubai.base.utils.GsonUtil;
import com.mob.mubai.base.utils.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lzw on 2016/12/17.
 */

public class MyClient {

        private static MyService myService;

        private static Retrofit retrofit;

        public MyClient() {
                retrofit = new Retrofit.Builder()
                          .client(OkHttpManager.getOkHttpClient())
                          .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                          .baseUrl("")
                          .build();
                if (null == myService) {
                        myService = retrofit.create(MyService.class);
                }
        }

        public MyService getMyService() {
                return myService;
        }
}
