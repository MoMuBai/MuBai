package com.mubai.progress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author: lzw
 * //
 * @date: 2017/5/23 上午10:37
 * //
 * @desc:
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    private int getLayout() {
        return R.layout.activity_main;
    }
}
