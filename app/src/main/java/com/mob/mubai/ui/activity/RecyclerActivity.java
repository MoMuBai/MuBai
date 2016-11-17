package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.ui.adapter.Adapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lzw on 16/11/17.
 */

public class RecyclerActivity extends BaseActivity implements Adapter.IonSlidingViewClickListener {

        @Bind(R.id.recycler_view)
        RecyclerView mRecyclerView;


        private Adapter mAdapter;


        @Override
        protected int getLayout() {
                return R.layout.activity_recycler;
        }

        @Override
        protected void initView() {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.setAdapter(mAdapter = new Adapter(this));
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        @Override
        protected void initData() {

        }

        @Override
        public void onItemClick(View view, int position) {
                Log.i(TAG, "点击项：" + position);
        }

        @Override
        public void onDeleteBtnCilck(View view, int position) {
                Log.i(TAG, "删除项：" + position);
                mAdapter.removeData(position);
        }
}
