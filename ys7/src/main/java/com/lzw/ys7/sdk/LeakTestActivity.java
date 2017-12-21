package com.lzw.ys7.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lzw.ys7.R;

/**
 * @author: lzw
 * @date: 21/12/2017 11:07 AM
 * @desc:
 */

public class LeakTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_test);
    }
}
