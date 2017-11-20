package com.mb.mubai.ui.test.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzw.library.utils.JavaScriptUtil;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * //
 * @date: 2017/4/17 下午2:14
 * //
 * @desc: H5主要缓存机制：
 */

public class WebViewActivity extends BaseActivity {
    @Bind(R.id.title_text)
    TextView titleText;
    @Bind(R.id.main_view)
    LinearLayout mainView;
    private WebView webView;

    private WebSettings webSettings;

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
        webView.loadUrl("https://www.baidu.com/");
    }

    @Override
    protected void initData() {

    }
}
