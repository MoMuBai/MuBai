package com.mob.mubai.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzw on 2016/12/18.
 */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

        public List<T> mList = new ArrayList<>();

        public abstract void onBindItemViewHolder(VH holder, T data, int position);

        public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                return onCreateItemViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (mList.size() <= 0) {
                        return;
                }
                onBindItemViewHolder((VH) holder, mList.get(position), position);
        }

        @Override
        public int getItemCount() {
                return null != mList && mList.size() > 0 ? mList.size() : 0;
        }

        @Override
        public int getItemViewType(int position) {
                return super.getItemViewType(position);
        }

        public void addRefreshData(List<T> data) {
                if (data == null) return;
                this.mList.clear();
                this.mList.addAll(data);
                notifyDataSetChanged();
        }
}
