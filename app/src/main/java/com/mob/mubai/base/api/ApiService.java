package com.mob.mubai.base.api;


import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lzw on 2016/12/16.
 */
public interface ApiService {

        @FormUrlEncoded
        @POST(ApiUrl.LoginUrl)
        Observable<DataResult<LoginValue>> login(@Field("name") String name, @Field("pass") String pass);

}
