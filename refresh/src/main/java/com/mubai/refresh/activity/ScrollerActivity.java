package com.mubai.refresh.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.mubai.refresh.R;
import com.mubai.refresh.view.ScrollerLayout;

/**
 * @author: lzw
 * @date: 2018/2/3 下午3:33
 * @desc:
 */

public class ScrollerActivity extends AppCompatActivity {

    private ScrollerLayout layout;

    private Button scrollBy, scrollTo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        layout = (ScrollerLayout) findViewById(R.id.layout);
        scrollBy = (Button) findViewById(R.id.scroll_by);
        scrollTo = (Button) findViewById(R.id.scroll_to);
        scrollBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.start();
            }
        });
        scrollTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.scrollTo(-60, -100);
            }
        });
    }
}
