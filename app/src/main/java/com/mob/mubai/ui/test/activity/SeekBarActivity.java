package com.mob.mubai.ui.test.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.To;
import com.mob.mubai.ui.test.contract.SeekBarContract;
import com.mob.mubai.ui.test.model.SeekBarModel;
import com.mob.mubai.ui.test.presenter.SeekBarPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/4
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  SeekBar
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class SeekBarActivity extends BaseActivity<SeekBarPresenter, SeekBarModel> implements SeekBarContract.View {

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
                mPresenter.myPost();
//                mPresenter.start();
        }

        @OnClick(R.id.back)
        void back() {
                finish();
        }

        @Override
        public void show(String str) {
                To.d(str);
        }

        @Override
        public void show() {

        }
}
