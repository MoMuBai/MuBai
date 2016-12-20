package com.mob.mubai.base.utils;

import android.content.Context;
import android.text.TextUtils;

import com.mob.mubai.App;
import com.mob.mubai.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.CertificatePinner;
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

        private static String hosts[] = {"https//:aaaa,com”, “https//:bbb.com"};

        private static int[] certificates = {R.raw.baidu};

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
                                                  //添加证书Pinning
                                                  .certificatePinner(new CertificatePinner.Builder()
                                                            .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
                                                            .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
                                                            .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
                                                            .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
                                                            .build())
                                                  .socketFactory(getSSLSocketFactory(App.getInstance(), certificates))
                                                  .hostnameVerifier(getHostnameVerifier(hosts))
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

        /**
         * 绑定证书
         */
        protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
                if (context == null) {
                        throw new NullPointerException("context == null");
                }
                CertificateFactory certificateFactory;
                try {
                        certificateFactory = CertificateFactory.getInstance("X.509");
                        KeyStore keyStore = null;
                        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                        keyStore.load(null, null);
                        for (int i = 0; i < certificates.length; i++) {
                                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                                if (certificate != null) {
                                        certificate.close();
                                }
                        }
                        SSLContext sslContext = null;
                        sslContext = SSLContext.getInstance("TLS");
                        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init(keyStore);
                        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                        return sslContext.getSocketFactory();
                } catch (CertificateException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                } catch (KeyStoreException e) {
                        e.printStackTrace();
                } catch (KeyManagementException e) {
                        e.printStackTrace();
                }
                return null;
        }

        /**
         * 构建HostnameVerifier
         */
        protected static HostnameVerifier getHostnameVerifier(final String[] hostUrls) {

                HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {

                        public boolean verify(String hostname, SSLSession session) {
                                boolean ret = false;
                                for (String host : hostUrls) {
                                        if (host.equalsIgnoreCase(hostname)) {
                                                ret = true;
                                        }
                                }
                                return ret;
                        }
                };

                return TRUSTED_VERIFIER;
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
