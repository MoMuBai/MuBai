package com.mb.mubai.ui.test.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;

import butterknife.Bind;

/**
 * Author: lzw
 * Date: 2018/9/19
 * Description: This is ViewTestActivity
 */

public class ViewTestActivity extends BaseActivity {


    @Bind(R.id.webView)
    WebView webView;

    private WebSettings mWebSettings;

    private String url;

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
        return R.layout.activity_view_test;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        this.mWebSettings = this.webView.getSettings();
        this.mWebSettings.setDomStorageEnabled(true);
        this.mWebSettings.setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(this, "WebTest");
        this.webView.removeJavascriptInterface("searchBoxJavaBridge_");
        this.webView.removeJavascriptInterface("accessibility");
        this.webView.removeJavascriptInterface("accessibilityTraversal");
        this.mWebSettings.setSavePassword(false);
        this.mWebSettings.setAllowFileAccess(false);
        this.mWebSettings.setAllowFileAccessFromFileURLs(false);
        this.mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        this.mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(ViewTestActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });

    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void back(View view) {
        webView.evaluateJavascript("javascript:MyTest()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                if (value.contains("aaa")) {
                    finish();
                } else {
                    webView.goBack();
                }
            }
        });
    }

}

