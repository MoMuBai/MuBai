package com.mb.mubai.base.service;

import com.mb.mubai.base.api.ApiUrl;
import com.mb.mubai.data.DataResult;
import com.mb.mubai.data.bean.LoginValue;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lzw on 2016/11/10.
 */

public interface MyService {

        //消息页面
        @FormUrlEncoded
        @POST(ApiUrl.LoginUrl)
        Observable<DataResult<LoginValue>> login(@Field("name") String name, @Field("pass") String pass);

}
