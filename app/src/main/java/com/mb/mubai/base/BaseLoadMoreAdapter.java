package com.mb.mubai.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mb.mubai.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lzw on 2016/12/18.
 */
public abstract class BaseLoadMoreAdapter<T, VH extends RecyclerView.ViewHolder> extends BaseRecyclerAdapter<T, VH> {
        public static final int TYPE_HEADER = -1;
        public static final int item_type = 0;
        public static final int TYPE_FOOTER = Integer.MAX_VALUE;

        private boolean hasMore = true, isLoading = true;
        private String headNum;
        private boolean addHead = false, isShow = false, isShowFootHint = false;

        public void setShowFootHint(boolean showFootHint) {
                isShowFootHint = showFootHint;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == TYPE_HEADER) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_view, parent, false);
                        return new HeadViewHolder(view);
                } else if (viewType == TYPE_FOOTER) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view, parent, false);
                        return new FootViewHolder(view);
                } else {
                        return onCreateItemViewHolder(parent, viewType);
                }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if (holder instanceof HeadViewHolder) {
                        HeadViewHolder itemView = (HeadViewHolder) holder;
                        itemView.houseNum.setText(headNum);
                } else if (holder instanceof FootViewHolder) {
                        holder.itemView.setBackgroundResource(R.color.bg_hui);
                        if (isShow) {
                                ((FootViewHolder) holder).textView.setVisibility(View.VISIBLE);
                                if (hasMore) {
                                        ((FootViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
                                        ((FootViewHolder) holder).textView.setText("正在加载...");
                                } else {
                                        ((FootViewHolder) holder).progressBar.setVisibility(View.GONE);
                                        ((FootViewHolder) holder).textView.setText("已到达底部");
                                }
                        } else {
                                if (isShowFootHint) {
                                        ((FootViewHolder) holder).progressBar.setVisibility(View.GONE);
                                        ((FootViewHolder) holder).textView.setText("已到达底部");
                                } else {
                                        ((FootViewHolder) holder).progressBar.setVisibility(View.GONE);
                                        ((FootViewHolder) holder).textView.setVisibility(View.GONE);
                                }
                        }
                } else {
                        if (addHead) {
                                super.onBindViewHolder(holder, position - 1);
                        } else {
                                super.onBindViewHolder(holder, position);
                        }
                }
        }

        @Override
        public int getItemViewType(int position) {
                if (addHead) {
                        if (position == 0) {
                                return TYPE_HEADER;
                        } else if (position == mList.size() + 1) {
                                return TYPE_FOOTER;
                        } else {
                                return item_type;
                        }
                } else {
                        if (position == mList.size()) {
                                return TYPE_FOOTER;
                        } else {
                                return item_type;
                        }
                }
        }

        @Override
        public int getItemCount() {
                if (addHead) {
                        return mList.size() + 2;
                } else {
                        return mList.size() + 1;
                }
        }

        public void addLoadMoreData(List<T> data) {
                if (data == null) return;
                this.mList.addAll(data);
                this.hasMore = data.size() == 10;
                isShow = true;
                this.isLoading = false;
                notifyDataSetChanged();
        }

        public void addRefreshData(List<T> data) {
                if (data == null) return;
                this.mList.clear();
                this.mList.addAll(data);
                this.hasMore = data.size() == 10;
                if (data.size() < 10) {
                        isShow = false;
                } else {
                        isShow = true;
                }
                this.isLoading = false;
                notifyDataSetChanged();
        }

        public boolean canLoadMore() {
                return hasMore && !isLoading;
        }

        public void addHeadView(String headNum) {
                this.headNum = headNum;
                this.addHead = true;
        }

        public void setLoading(boolean loading) {
                isLoading = loading;
        }

        public static class HeadViewHolder extends RecyclerView.ViewHolder {
                @Bind(R.id.houseNum)
                TextView houseNum;

                public HeadViewHolder(View itemView) {
                        super(itemView);
                        ButterKnife.bind(this, itemView);
                }
        }

        public static class FootViewHolder extends RecyclerView.ViewHolder {
                @Bind(R.id.progress_view)
                ProgressBar progressBar;
                @Bind(R.id.tv_content)
                TextView textView;

                public FootViewHolder(View itemView) {
                        super(itemView);
                        ButterKnife.bind(this, itemView);
                }
        }
}
