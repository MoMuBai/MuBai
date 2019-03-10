package com.mb.mubai.ui.test.activity;

import android.content.DialogInterface;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzw.library.utils.JavaScriptUtil;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import butterknife.BindView;

/**
 * @author: lzw
 * //
 * @date: 2017/4/17 下午2:14
 * //
 * @desc: H5主要缓存机制：
 */

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.main_view)
    LinearLayout mainView;
    private WebView webView;

    private WebSettings webSettings;

    @Override
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        webView = new WebView(this);
        mainView.addView(webView);
        webSettings = webView.getSettings();
        /**
         * Dom Storage 缓存的支持，需要打开Web Storage
         * 优势：较大的存储空间
         * 场景：临时、简单的数据存储，cookie的扩展
         */
        webSettings.setDomStorageEnabled(true);
        /**
         * Web SQL DataBase
         * 优势：存储管理复杂的数据结构
         * 场景：不推荐使用，用IndexDB替代
         */
        webSettings.setDatabaseEnabled(true);
        String dbPath = getApplicationContext().getDir("db", MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(dbPath);

        /**
         * ApplicationCache
         * 优势：方便构建离线App，静态文件的缓存
         * 场景：离线App，不推荐使用
         */
        webSettings.setAppCacheEnabled(true);
        final String cachePath = getApplicationContext().getDir("cache", MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(cachePath);
        webSettings.setAppCacheMaxSize(5 * 1024 * 1024);


        /**
         * IndexDB
         * 优势：存储任意数据结构，使用简单，支持索引
         * 场景：结构、关系复杂的数据存储，WebSqlDataBase的替代
         * 只需要打开Js的支持就可以
         */
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JavaScriptUtil(mActivity, webView), "android");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                /**
                 *证书出现信任问题的时候默认会调用handler.cancel();
                 * super.onReceivedSslError(view, handler, error);
                 */

                /**
                 * 有时候我们的证书只是因为系统的时间不对、跟证书被删掉等问题导致的SSL错误
                 * 这时候我们是可以让程序去忽略SSL错误的
                 *
                 * 其他时候就有可能是证书本身错误异常导致的
                 * 这时候我们就要去保留SSL错误
                 *
                 * 获取证书的SHA256值
                 * http://www.atool.org/file_hash.php
                 */

                if (error.getPrimaryError() == SslError.SSL_INVALID) {
                    // 如果手动校验sha256成功就允许加载页面
                    if (isSSLCertOk(error.getCertificate(), "a10060271baeb6db3718cf6a42049eeb2b3bf4c011e2f8942bc257ac65b0246a")) {
                        handler.proceed();
                    } else {
                        try {
                            new AlertDialog.Builder(WebViewActivity.this)
                                    .setTitle("警告")
                                    .setMessage("证书校验失败")
                                    .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            System.exit(0);
                                            dialog.dismiss();
                                        }
                                    }).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    handler.cancel();
                }
            }
        });
        //移除部分能被远程任意代码威胁的接口
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        //WebView密码明文存储漏洞
        webSettings.setSavePassword(false);
        //防止越权访问，跨域等安全问题
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webView.loadUrl("https://111.205.207.105:10079/wapserver_uat/outer/index.html?seq=DZZH_2018013101143&reuri=YBActivity&status=0&CooperCode=MM00000014&ActivityId=YB00000086&Page=msyb&ChannelId=mszx02615");
    }

    @Override
    protected void initData() {
    }


    /**
     * SSL证书错误，手动校验https证书
     *
     * @param cert      https证书
     * @param sha256Str sha256值
     * @return true通过，false失败
     */
    public static boolean isSSLCertOk(SslCertificate cert, String sha256Str) {
        byte[] SSLSHA256 = hexToBytes(sha256Str);
        Bundle bundle = SslCertificate.saveState(cert);
        if (bundle != null) {
            byte[] bytes = bundle.getByteArray("x509-certificate");
            if (bytes != null) {
                try {
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    Certificate ca = cf.generateCertificate(new ByteArrayInputStream(bytes));
                    MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                    byte[] key = sha256.digest(((X509Certificate) ca).getEncoded());
                    return Arrays.equals(key, SSLSHA256);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * hexString转byteArr
     * <p>例如：</p>
     * hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }
     *
     * @param hexString
     * @return 字节数组
     */
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.trim().length() == 0) {
            return null;
        }
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 两个字符对应一个byte
            int h = hexDigits.indexOf(hexChars[pos]) << 4; // 注1
            int l = hexDigits.indexOf(hexChars[pos + 1]); // 注2
            if (h == -1 || l == -1) { // 非16进制字符
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }
}
