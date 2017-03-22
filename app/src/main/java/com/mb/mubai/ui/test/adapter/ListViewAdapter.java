package com.mb.mubai.ui.test.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lzw.library.base.BaseAdapter;
import com.mb.mubai.R;
import com.mb.mubai.view.widget.DownListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午8:24
 * //
 * @desc:
 */

public class ListViewAdapter extends BaseAdapter<String, ListViewAdapter.ViewHolder> {

    public ListViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindData(ViewHolder holder, String data, int pos) {
        holder.mTextView.setText(data);
    }

    @Override
    protected ViewHolder onCreateHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int onItemCreate() {
        return R.layout.down_list_item;
    }

    public class ViewHolder extends BaseAdapter.ViewHolder {

        @Bind(R.id.test_text)
        TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
