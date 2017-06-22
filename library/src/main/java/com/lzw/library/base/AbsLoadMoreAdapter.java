package com.lzw.library.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.lzw.library.R;

import java.util.List;


/**
 * @author: lzw
 * //
 * @date: 2017/6/21 下午4:42
 * //
 * @desc:
 */

public abstract class AbsLoadMoreAdapter<T, HEAD_VH extends RecyclerView.ViewHolder, CONTENT_VH extends RecyclerView.ViewHolder, FOOT_VH extends RecyclerView.ViewHolder> extends AbsRecyclerAdapter<T, CONTENT_VH> {

    private static final int HEADER_TYPE = -1;

    private static final int FOOTER_TYPE = Integer.MAX_VALUE;

    private static final int CONTENT_TYPE = 0;

    public boolean isAddHead = false, isAddFoot = false;

    private boolean hasMore = true, isLoading = true, isShow = false;

    public AbsLoadMoreAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        if (isAddHead) {
            if (isAddFoot) {
                return mList.size() + 2;
            } else {
                return mList.size() + 1;
            }
        } else {
            if (isAddFoot) {
                return mList.size() + 1;
            } else {
                return mList.size();
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (isAddHead) {
            if (isAddFoot) {
                if (position == 0) {
                    return HEADER_TYPE;
                } else if (position == mList.size() + 1) {
                    return FOOTER_TYPE;
                } else {
                    return CONTENT_TYPE;
                }
            } else {
                if (position == 0) {
                    return HEADER_TYPE;
                } else {
                    return CONTENT_TYPE;
                }
            }
        } else {
            if (isAddFoot) {
                if (position == mList.size()) {
                    return FOOTER_TYPE;
                } else {
                    return CONTENT_TYPE;
                }
            } else {
                return CONTENT_TYPE;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEADER_TYPE) {
            onAbsBindHeadHolder((HEAD_VH) holder, position);
        } else if (getItemViewType(position) == FOOTER_TYPE) {
            onAbsBindFootHolder((FOOT_VH) holder, position);
        } else {
            if (isAddHead) {
                super.onBindViewHolder(holder, position - 1);
            } else {
                super.onBindViewHolder(holder, position);
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            if (0 != onAbsItemHeadCreate()) {
                View view = mLayoutInflater.inflate(onAbsItemHeadCreate(), parent, false);
                return onCreateHeadViewHolder(view);
            } else {
                throw new NullPointerException("the header layout is null -- by lzw");
            }
        } else if (viewType == FOOTER_TYPE) {
            if (0 != onAbsItemFootCreate()) {
                View view = mLayoutInflater.inflate(onAbsItemFootCreate(), parent, false);
                return onCreateFootViewHolder(view);
            } else {
                throw new NullPointerException("the footer layout is null -- by lzw");
            }
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    /*----------------暴露出去的方法-------------**/


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

    protected abstract RecyclerView.ViewHolder onCreateFootViewHolder(View view);

    protected abstract RecyclerView.ViewHolder onCreateHeadViewHolder(View view);

    public abstract int onAbsItemHeadCreate();

    public abstract int onAbsItemFootCreate();

    public abstract void onAbsBindFootHolder(FOOT_VH holder, int position);

    public abstract void onAbsBindHeadHolder(HEAD_VH holder, int position);

}
