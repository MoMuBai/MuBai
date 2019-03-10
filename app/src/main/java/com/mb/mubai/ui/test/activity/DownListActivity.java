package com.mb.mubai.ui.test.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.listener.DownListHelper;
import com.mb.mubai.ui.test.adapter.ListViewAdapter;
import com.mb.mubai.ui.test.contract.DownListContract;
import com.mb.mubai.ui.test.model.DownListModel;
import com.mb.mubai.ui.test.presenter.DownListPresenter;
import com.mb.mubai.view.widget.DownListView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午2:57
 * //
 * @desc: 下拉列表Activity
 */

public class DownListActivity extends BaseActivity<DownListPresenter, DownListModel> implements DownListContract.View, DownListHelper {
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.down_list_view)
    DownListView downListView;
    @BindView(R.id.list_view)
    ListView listView;

    private ListViewAdapter adapter;

    @Override
    protected DownListModel getModel() {
        return new DownListModel();
    }

    @Override
    protected DownListPresenter getPresenter() {
        return new DownListPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_down_list;
    }

    @Override
    protected void initView() {
        downListView.setmDownListHelper(this);
    }

    @Override
    protected void initData() {
        adapter = new ListViewAdapter(mContext);
        listView.setAdapter(adapter);
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4})
    void click(View v) {
        switch (v.getId()) {
            case R.id.text1:
                mPresenter.getData("1");
                downListView.onClick();
                break;
            case R.id.text2:
                mPresenter.getData("2");
                downListView.onClick();
                break;
            case R.id.text3:
                mPresenter.getData("3");
                downListView.onClick();
                break;
            case R.id.text4:
                mPresenter.getData("4");
                downListView.onClick();
                break;
            default:
                break;
        }
    }

    @Override
    public void showData(List<String> datas) {
        downListView.setData(datas);
    }

    @Override
    public void showMainData(List<String> datas) {
        adapter.addRefreshData(datas);
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

    @Override
    public void onSelectionChanged(int pos) {
        Toast.makeText(mContext, "pos:" + pos, Toast.LENGTH_SHORT).show();
    }
}
