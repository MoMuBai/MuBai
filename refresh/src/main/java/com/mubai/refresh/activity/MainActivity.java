package com.mubai.refresh.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mubai.refresh.R;
import com.mubai.refresh.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/4/5 上午11:03
 * //
 * @desc:
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "REFRESH";

    private RefreshLayout refreshLayout;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(MainActivity.this, "刷新完成了", Toast.LENGTH_SHORT).show();
            refreshLayout.setRefreshFinish();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (RefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setRefreshListener(new RefreshLayout.RefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(runnable, 4000);
            }
        });
    }

}


