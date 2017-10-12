package com.mb.mubai.base.api.user;


import com.mb.mubai.base.network.GsonUtil;
import com.mb.mubai.base.network.OkHttpManager;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by lzw on 2016/12/17.
 */

public class UserClient {

    private static UserService userService;

    private static Retrofit retrofit;

    public UserClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(OkHttpManager.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        if (null == userService) {
            userService = retrofit.create(UserService.class);
        }
    }

    public UserService getMyService() {
        return userService;
    }
}
