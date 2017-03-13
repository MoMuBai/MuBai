package com.mb.mubai.base.api.found;

import com.mb.mubai.base.network.GsonUtil;
import com.mb.mubai.base.network.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2017/3/13.
 */

public class FoundClient {
    private static FoundService foundService;

    private static Retrofit retrofit;

    public FoundClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(OkHttpManager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        if (null == foundService) {
            foundService = retrofit.create(FoundService.class);
        }
    }

    public FoundService getHomeService() {
        return foundService;
    }
}
