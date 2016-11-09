package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;

/**
 * Created by mubai on 2016/11/4.
 */

public class SeekBarActivity extends BaseActivity{


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_seekbar;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
