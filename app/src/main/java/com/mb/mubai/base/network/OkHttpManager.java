package com.mb.mubai.base.network;

import android.content.Context;
import android.util.Log;

import com.lzw.library.utils.IsNetUtil;
import com.mb.mubai.App;
import com.mb.mubai.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.CertificatePinner;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

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
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor((message) -> {
                        try {
                            String text = URLDecoder.decode(message, "utf-8");
                            Log.d("OkHttpManager----", text);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            Log.d("OkHttpManager----", message);
                        }
                    });
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
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
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new HttpCacheInterceptor())
                            .cache(cache)
                            .build();

                }
            }
        }
        return okHttpClient;
    }


    /**
     * 错误的证书验证方法
     * 忽略了检验服务端证书
     *
     * @return Error
     * @throws Exception
     */
    public static SSLSocketFactory getErrorSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }


    /**
     * 客户端没有内置证书的情况
     *
     * @return
     * @throws Exception
     */
    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            //证书中的公钥
            public static final String PUB_KEY = "3082010a0282010100d52ff5dd432b3a05113ec1a7065fa5a80308810e4e181cf14f7598c8d553cccb7d5111fdcdb55f6ee84fc92cd594adc1245a9c4cd41cbe407a919c5b4d4a37a012f8834df8cfe947c490464602fc05c18960374198336ba1c2e56d2e984bdfb8683610520e417a1a9a5053a10457355cf45878612f04bb134e3d670cf96c6e598fd0c693308fe3d084a0a91692bbd9722f05852f507d910b782db4ab13a92a7df814ee4304dccdad1b766bb671b6f8de578b7f27e76a2000d8d9e6b429d4fef8ffaa4e8037e167a2ce48752f1435f08923ed7e2dafef52ff30fef9ab66fdb556a82b257443ba30a93fda7a0af20418aa0b45403a2f829ea6e4b8ddbb9987f1bf0203010001";

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {

            }

            //客户端并为对ssl证书的有效性进行校验
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
                if (chain == null) {
                    throw new IllegalArgumentException("checkServerTrusted:x509Certificate array isnull");
                }

                if (!(chain.length > 0)) {
                    throw new IllegalArgumentException("checkServerTrusted: X509Certificate is empty");
                }

                if (!(null != authType && authType.equalsIgnoreCase("RSA"))) {
                    throw new CertificateException("checkServerTrusted: AuthType is not RSA");
                }

                // Perform customary SSL/TLS checks
                try {
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
                    tmf.init((KeyStore) null);
                    for (TrustManager trustManager : tmf.getTrustManagers()) {
                        ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
                    }
                } catch (Exception e) {
                    throw new CertificateException(e);
                }
                // Hack ahead: BigInteger and toString(). We know a DER encoded Public Key begins
                // with 0×30 (ASN.1 SEQUENCE and CONSTRUCTED), so there is no leading 0×00 to drop.
                RSAPublicKey pubkey = (RSAPublicKey) chain[0].getPublicKey();

                String encoded = new BigInteger(1 /* positive */, pubkey.getEncoded()).toString(16);
                // Pin it!
                final boolean expected = PUB_KEY.equalsIgnoreCase(encoded);

                if (!expected) {
                    throw new CertificateException("checkServerTrusted: Expected public key: "
                            + PUB_KEY + ", got public key:" + encoded);
                }
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }

    /**
     * 客户端内置证书的情况
     * 需要实现本地证书的加载
     *
     * @param context
     * @param certificates
     * @return
     */
    protected static SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
        if (context == null) {
            throw new NullPointerException("context == null");
        }

        //CertificateFactory用来证书生成
        CertificateFactory certificateFactory;
        try {
            certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = null;
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (int i = 0; i < certificates.length; i++) {
                //读取本地证书
                InputStream certificate = context.getResources().openRawResource(certificates[i]);
                keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(certificate));
                if (certificate != null) {
                    certificate.close();
                }
            }

            //Create an SSLContext that uses our TrustManager
            SSLContext sslContext = null;
            sslContext = SSLContext.getInstance("TLS");

            //Create a TrustManager that trusts the CAs in our keyStore
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
     * 主机名验证器
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

                Log.d("Okhttp", "no network");
            }

            Response originalResponse = chain.proceed(request);
            if (IsNetUtil.isNetConnected(App.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .addHeader("token", "token")
                        .addHeader("k", "key")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Cache-Control", cacheControl)
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

    /**
     * 请求和返回日志
     */
    static class HttpCacheInterceptor2 implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String token = "";
            Request request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            //打印请求信息
            Log.d("RetrofitLog:", "url:" + request.url());
            Log.d("RetrofitLog:", "method:" + request.method());

            //记录请求耗时
            long startNs = System.nanoTime();
            okhttp3.Response response;
            try {
                //发送请求，获得相应，
                response = chain.proceed(request);
            } catch (Exception e) {
                throw e;
            }
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            //打印请求耗时
            Log.d("RetrofitLog:", "耗时:" + tookMs + "ms");
            //使用response获得headers(),可以更新本地Cookie。
            Log.d("RetrofitLog:", "headers==========");
            Headers headers = response.headers();
            Log.d("RetrofitLog:", headers.toString());

            //获得返回的body，注意此处不要使用responseBody.string()获取返回数据，原因在于这个方法会消耗返回结果的数据(buffer)
            ResponseBody responseBody = response.body();

            //为了不消耗buffer，我们这里使用source先获得buffer对象，然后clone()后使用
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            //获得返回的数据
            Buffer buffer = source.buffer();
            //使用前clone()下，避免直接消耗
            Log.d("RetrofitLog:", "response:" + buffer.clone().readString(Charset.forName("UTF-8")));

            return response;
        }
    }

}
