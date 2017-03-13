package com.mb.mubai.base.api.home;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public interface HomeService {
    //消息页面
    @FormUrlEncoded
    @POST("getData")
    Observable<String> getHomeData(@Field("userId") String userId);
}
