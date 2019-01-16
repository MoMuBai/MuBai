package com.mb.mubai.ui.test.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.ui.test.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author: lzw
 * Date: 2018/7/6
 * Description: This is TestActivity
 */

public class TestActivity extends BaseActivity {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private TestAdapter adapter;

    @Override
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new TestAdapter(mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("");
        }
        adapter.addData(data);
    }

}
