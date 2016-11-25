package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzw on 2016/11/4.
 */

public class SeekBarActivity extends BaseActivity {


    @Bind(R.id.img1)
    ImageView img1;
    @Bind(R.id.txt1)
    TextView txt1;
    @Bind(R.id.back)
    RelativeLayout back;
    @Bind(R.id.img2)
    TextView img2;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.seek_bar)
    SeekBar seekBar;
    @Bind(R.id.seek_bar_other)
    SeekBar seekBarOther;
    @Bind(R.id.activity_second)
    LinearLayout activitySecond;

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

    @OnClick(R.id.back)
    void back() {
        finish();
    }
}
