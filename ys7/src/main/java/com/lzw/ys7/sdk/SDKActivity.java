package com.lzw.ys7.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lzw.ys7.R;
import com.videogo.openapi.EZOpenSDK;

/**
 * @author: lzw
 * @date: 13/12/2017 3:24 PM
 * @desc:
 */

public class SDKActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);
        findViewById(R.id.login_page_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EZOpenSDK.getInstance().openLoginPage();
            }
        });
    }
}
