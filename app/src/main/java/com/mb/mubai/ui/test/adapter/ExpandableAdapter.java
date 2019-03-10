package com.mb.mubai.ui.test.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lzw.library.base.BaseAdapter;
import com.mb.mubai.R;
import com.mb.mubai.view.widget.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * //
 * @date: 2017/3/22 上午9:43
 * //
 * @desc:
 */

public class ExpandableAdapter extends BaseAdapter<String, ExpandableAdapter.ViewHolder> {

    private SparseBooleanArray mSparseBooleanArray;

    public ExpandableAdapter(Context context) {
        super(context);
        mSparseBooleanArray = new SparseBooleanArray();
    }

    @Override
    protected void onBindData(ViewHolder holder, String data, int pos) {
        holder.expandTextView.setText(data, mSparseBooleanArray, pos);
    }

    @Override
    protected ViewHolder onCreateHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int onItemCreate() {
        return R.layout.item_expandable;
    }

    static class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.tvAnswer)
        TextView tvAnswer;
        @BindView(R.id.ivImg)
        ImageButton ivImg;
        @BindView(R.id.expand_text_view)
        ExpandableTextView expandTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
