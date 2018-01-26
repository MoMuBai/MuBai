package com.mb.mubai.ui.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * @date: 2018/1/26 下午5:20
 * @desc:
 */

public class RecyclerViewAdapter extends BaseRecyclerAdapter<String, RecyclerViewAdapter.MyViewHolder> {

    @Override
    protected int getItemLayout() {
        return R.layout.item_recycler_view_move;
    }

    @Override
    public MyViewHolder onCreateItemViewHolder(View view, ViewGroup parent, int viewType) {
        return new MyViewHolder(view);
    }

    @Override
    public MyViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindItemViewHolder(MyViewHolder holder, String data, int position) {
        holder.text.setOnClickListener(v -> {
            Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text)
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
