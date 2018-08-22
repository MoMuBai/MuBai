package com.lzw.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/6/21 下午4:35
 * //
 * @desc:
 */

public abstract class AbsRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    public List<T> mList;

    public LayoutInflater mLayoutInflater;

    public Context mContext;

    public AbsRecyclerItemClick absRecyclerItemClick;

    public AbsRecyclerLongItemClick absRecyclerLongItemClick;

    public void setAbsRecyclerItemClick(AbsRecyclerItemClick absRecyclerItemClick) {
        this.absRecyclerItemClick = absRecyclerItemClick;
    }

    public void setAbsRecyclerLongItemClick(AbsRecyclerLongItemClick absRecyclerLongItemClick) {
        this.absRecyclerLongItemClick = absRecyclerLongItemClick;
    }

    public AbsRecyclerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (0 != onAbsItemCreate()) {
            View view = mLayoutInflater.inflate(onAbsItemCreate(), parent, false);
            return onAbsCreateItemViewHolder(view);
        } else {
            return onAbsCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mList.size() < 0) {
            return;
        }
        onAbsBindItemViewHolder((VH) holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return null != mList && mList.size() > 0 ? mList.size() : 0;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /*----------------暴露出去的方法-------------**/


    public abstract int onAbsItemCreate();

    public abstract void onAbsBindItemViewHolder(VH holder, T t, int position);

    public abstract VH onAbsCreateItemViewHolder(View view);

    public abstract VH onAbsCreateItemViewHolder(ViewGroup parent, int viewType);


    public void addRefreshData(List<T> data) {
        if (data == null) return;
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    public void addLoadMoreData(List<T> data) {
        if (data == null) return;
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    public interface AbsRecyclerItemClick {
        void itemClick(int pos);
    }

    public interface AbsRecyclerLongItemClick {
        void longItemClick(int pos);
    }

}
