package com.lzw.library.utils;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;


/**
 * Created by lzw on 2016/11/8.
 */

public class JavaScriptUtil {

    private Activity activity;

    private WebView webView;


    public JavaScriptUtil(Activity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }

    /**
     * 返回事件回调
     */
    @JavascriptInterface
    public void closeWebview() {
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    backView();
                }
            }
        });
    }

    private void backView() {
        activity.finish();
    }

}
