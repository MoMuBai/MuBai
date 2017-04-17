package com.mb.mubai.ui.test.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
 * @desc:
 */

public class WebViewActivity extends BaseActivity {
    @Bind(R.id.web_view)
    WebView webView;

    private WebSettings webSettings;

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        webSettings = webView.getSettings();
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
