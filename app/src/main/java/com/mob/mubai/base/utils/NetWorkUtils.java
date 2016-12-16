package com.mob.mubai.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mob.mubai.App;
import com.mob.mubai.base.api.ApiService;
import com.mob.mubai.base.helps.ResultJsonDeser;
import com.mob.mubai.data.DataResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzw on 2016/12/16.
 */
public final class NetWorkUtils {

        private static final String KEY = "n6elpebcs84yjeaj5ht7x0eii9z83iea8bec9szerejj7zy3";
        private String BASE_URL;

        private static ApiService apiService;

        private static Retrofit retrofit;

        private static NetWorkUtils instance;

        //设置拦截器添加x相同请求参数的头部 token
        private Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
                  .addHeader("token", "token")
                  .addHeader("k", KEY)
                  .addHeader("Content-Type", "application/json")
                  .build());


        public static NetWorkUtils getInstance() {
                if (instance == null) {
                        synchronized (NetWorkUtils.class) {
                                instance = new NetWorkUtils();
                        }
                }
                return instance;
        }

        private NetWorkUtils() {

                BASE_URL = SpUtils.getString(App.getInstance(), "BASE_URL");
//        日志拦截器
//                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                File cacheFile = new File(App.getInstance().getCacheDir(), "cache");
                Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                          .readTimeout(7676, TimeUnit.MILLISECONDS)
                          .connectTimeout(7676, TimeUnit.MILLISECONDS)
                          .addInterceptor(mInterceptor)
//                          .addInterceptor(interceptor)
                          .addNetworkInterceptor(new HttpCacheInterceptor())
                          .cache(cache)
                          .build();

                Gson gson = new GsonBuilder()
                          .registerTypeHierarchyAdapter(DataResult.class, new ResultJsonDeser()).
                                    setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();

                retrofit = new Retrofit.Builder()
                          .client(okHttpClient)
                          .addConverterFactory(GsonConverterFactory.create(gson))
                          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                          .baseUrl(BASE_URL)
                          .build();
                if (null == apiService) {
                        apiService = retrofit.create(ApiService.class);
                }
        }


        /**
         * 返回一个ApiService
         */
        public ApiService getApiService() {
                return apiService;
        }

        class HttpCacheInterceptor implements Interceptor {

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
