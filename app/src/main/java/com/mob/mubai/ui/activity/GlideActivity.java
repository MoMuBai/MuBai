package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mglide.MGlideHelper;
import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lzw on 2016/12/23.
 * 引导页
 */

public class GlideActivity extends BaseActivity {

        @Bind(R.id.glide_helper)
        MGlideHelper mGlideHelper;

        private List<ImageView> viewList;

        @Override
        protected int getLayout() {
                return R.layout.activity_glide;
        }

        @Override
        protected void initView() {
                viewList = new ArrayList<>();
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.mubai);
                viewList.add(imageView);
                viewList.add(imageView);
                viewList.add(imageView);
                mGlideHelper.setBgView(viewList);
        }

        @Override
        protected void initData() {

        }
}
