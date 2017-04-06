package com.mubai.refresh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mubai.refresh.R;
import com.mubai.refresh.view.RefreshLayout;

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

    private TextView textView1, textView2;

    private ProgressBar progressBar1, progressBar2;

    private Handler handler = new Handler();

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            textView1.setText("刷新完成了");
            progressBar1.setVisibility(View.GONE);
            refreshLayout.setRefreshFinish();
        }
    };

    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            textView2.setText("加载完成了");
            progressBar2.setVisibility(View.GONE);
            refreshLayout.setLoadFinish();
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (RefreshLayout) findViewById(R.id.refresh_layout);
        textView1 = (TextView) findViewById(R.id.text1);
        progressBar1 = (ProgressBar) findViewById(R.id.progress1);
        textView2 = (TextView) findViewById(R.id.text2);
        progressBar2 = (ProgressBar) findViewById(R.id.progress2);
        refreshLayout.setRefreshListener(new RefreshLayout.RefreshListener() {
            @Override
            public void onRefresh() {
                progressBar1.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable1, 3000);
            }
        });

        refreshLayout.setLoadListener(new RefreshLayout.LoadListener() {
            @Override
            public void onLoad() {
                progressBar2.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable2, 3000);
            }
        });
    }
}


