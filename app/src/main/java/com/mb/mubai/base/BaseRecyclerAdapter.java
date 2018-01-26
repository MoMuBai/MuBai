package com.mb.mubai.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mb.mubai.App;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzw
 * @date 2016/12/18
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater inflater;

    public BaseRecyclerAdapter() {
        mData = new ArrayList<>();
        mContext = App.getInstance();
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getItemLayout(), parent, false);
        return onCreateItemViewHolder(view, parent, viewType);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mData.size() <= 0) {
            return;
        }
        onBindItemViewHolder((VH) holder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return null != mData && mData.size() > 0 ? mData.size() : 0;
    }


    /**
     * 返回ItemLayout
     *
     * @return
     */
    protected abstract int getItemLayout();

    /**
     * 创建ViewHolder
     *
     * @param view
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VH onCreateItemViewHolder(View view, ViewGroup parent, int viewType);

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定数据
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract void onBindItemViewHolder(VH holder, T data, int position);


    public void addRefreshData(List<T> data) {
        if (data == null) {
            return;
        }
        if (null == this.mData) {
            this.mData = new ArrayList<>();
        }
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addLoadData(List<T> data) {
        if (data == null) {
            return;
        }
        if (null == mData) {
            mData = new ArrayList<>();
        }
        this.mData.addAll(data);
        notifyDataSetChanged();
    }
}
