package com.mob.mubai.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseFragment;

/**
 * Created by lzw on 2016/12/21.
 */

public class Fragment2 extends BaseFragment {


        public static Fragment getFragment() {
                Fragment f = new Fragment2();
                return f;
        }

        @Override
        protected View getLayout(LayoutInflater inflater, ViewGroup container) {
                return inflater.inflate(R.layout.activity_im, container,false);
        }

        @Override
        protected void initView(View view) {

        }

        @Override
        protected void initData() {

        }

        @Override
        protected void lazyLoad() {

        }
}
