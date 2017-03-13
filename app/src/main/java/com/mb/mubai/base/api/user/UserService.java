package com.mb.mubai.base.api.user;

import com.mb.mubai.base.api.ApiUrl;
import com.mb.mubai.data.DataResult;
import com.mb.mubai.data.bean.LoginValue;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lzw on 2016/11/10.
 */

public interface UserService {

    //消息页面
    @FormUrlEncoded
    @POST(ApiUrl.LoginUrl)
    Observable<DataResult<LoginValue>> login(@Field("name") String name, @Field("pass") String pass);


    @POST("url")
    Observable<String> getUserInfo(@Query("userId") String userId);
}
