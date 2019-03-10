package com.mb.mubai.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mb.mubai.R;
import com.mb.mubai.base.listener.DownListHelper;
import com.mb.mubai.ui.test.adapter.ListViewAdapter;

import java.util.List;


/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午3:49
 * //
 * @desc:
 */

public class DownListView extends LinearLayout {

    private Context mContext;

    private ListView mListView;

    private LinearLayout mLinearLayout;

    private ListViewAdapter mListViewAdapter;

    private DownListHelper mDownListHelper;

    public void setmDownListHelper(DownListHelper mDownListHelper) {
        this.mDownListHelper = mDownListHelper;
    }

    public DownListView(Context context) {
        this(context, null, 0);
    }

    public DownListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.down_list_layout, this, true);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linLayout);
        onItemClick();
    }


    public void setData(List<String> datas) {
        mListViewAdapter = new ListViewAdapter(mContext);
        mListViewAdapter.addRefreshData(datas);
        mListView.setAdapter(mListViewAdapter);
    }

    public void onClick() {
        if (mLinearLayout.getVisibility() == VISIBLE) {
            mLinearLayout.setVisibility(GONE);
        } else {
            mLinearLayout.setVisibility(VISIBLE);
        }
    }

    private void onItemClick() {

    }
}
