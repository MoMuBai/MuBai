package com.mob.mubai.base.utils;

import android.text.TextUtils;

import com.mob.mubai.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lzw on 2016/12/17.
 */

public class OkHttpManager {

        private static OkHttpClient okHttpClient;

        private static final String KEY = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3";

        //设置拦截器添加x相同请求参数的头部 token
        private static Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
                  .addHeader("token", "token")
                  .addHeader("k", "key")
                  .addHeader("Content-Type", "application/json")
                  .build());

        public static OkHttpClient getOkHttpClient() {
                if (null == okHttpClient) {
                        synchronized (OkHttpManager.class) {
                                if (null == okHttpClient) {
                                        //        日志拦截器
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                                        File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
                                        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
                                        okHttpClient = new OkHttpClient.Builder()
                                                  .readTimeout(7676, TimeUnit.MILLISECONDS)
                                                  .connectTimeout(7676, TimeUnit.MILLISECONDS)
                                                  .addInterceptor(mInterceptor)
//                          .addInterceptor(interceptor)
                                                  .addNetworkInterceptor(new HttpCacheInterceptor())
                                                  .cache(cache)
                                                  .build();

                                }
                        }
                }
                return okHttpClient;
        }


        static class HttpCacheInterceptor implements Interceptor {

                @Override
                public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (!IsNetUtil.isNetConnected(App.getInstance())) {
                                request = request.newBuilder()
                                          .cacheControl(CacheControl.FORCE_CACHE)
                                          .build();
                                L.d("Okhttp", "no network");
                        }

                        Response originalResponse = chain.proceed(request);
                        if (IsNetUtil.isNetConnected(App.getInstance())) {
                                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                                String cacheControl = request.cacheControl().toString();
                                return originalResponse.newBuilder()
                                          .header("Cache-Control", cacheControl)
                                          .removeHeader("Pragma")
                                          .build();
                        } else {
                                return originalResponse.newBuilder()
                                          .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                                          .removeHeader("Pragma")
                                          .build();
                        }
                }
        }

}
