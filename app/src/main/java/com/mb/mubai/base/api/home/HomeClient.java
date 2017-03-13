package com.mb.mubai.base.api.home;

import com.mb.mubai.base.network.GsonUtil;
import com.mb.mubai.base.network.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2017/3/13.
 */

public class HomeClient {
    private static HomeService homeService;

    private static Retrofit retrofit;

    public HomeClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(OkHttpManager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        if (null == homeService) {
            homeService = retrofit.create(HomeService.class);
        }
    }

    public HomeService getHomeService() {
        return homeService;
    }
}
