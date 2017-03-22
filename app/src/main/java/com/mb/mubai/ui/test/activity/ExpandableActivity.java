package com.mb.mubai.ui.test.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.ui.test.adapter.ExpandableAdapter;
import com.mb.mubai.ui.test.contract.ExpandableContract;
import com.mb.mubai.ui.test.model.ExpandableModel;
import com.mb.mubai.ui.test.presenter.ExpandablePresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * //
 * @date: 2017/3/22 上午9:37
 * //
 * @desc:
 */

public class ExpandableActivity extends BaseActivity<ExpandablePresenter, ExpandableModel> implements ExpandableContract.View {
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private ExpandableAdapter expandableAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_expandable;
    }

    @Override
    protected void initView() {
        expandableAdapter = new ExpandableAdapter(mContext);
        listView.setAdapter(expandableAdapter);
        swipeRefresh.setColorSchemeColors(Color.BLUE);
        swipeRefresh.setEnabled(false);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    View firstVisibleItemView = listView.getChildAt(0);
                    if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                        swipeRefresh.setEnabled(true);
                        //顶部
                        swipeRefresh.setOnRefreshListener(() -> {
                            mPresenter.getData();
                        });
                    }
                } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    View lastVisibleItemView = listView.getChildAt(listView.getChildCount() - 1);
                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == lastVisibleItemView.getHeight()) {
                        //底部
                    }
                } else {
                    swipeRefresh.setEnabled(false);
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void showData(List<String> data) {
        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.postDelayed(() -> {
                swipeRefresh.setRefreshing(false);
            }, 500);
        }
        expandableAdapter.addRefreshData(data);
    }

    @Override
    public void showStart() {

    }

    @Override
    public void showNoData(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showStop() {

    }
}
