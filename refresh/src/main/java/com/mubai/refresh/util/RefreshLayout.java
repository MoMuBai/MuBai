package com.mubai.refresh.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mubai.refresh.R;

/**
 * @author: lzw
 * //
 * @date: 2017/4/5 上午11:17
 * //
 * @desc: 自定义刷新控件
 */

public class RefreshLayout extends LinearLayout {

    private static final String TAG = "REFRESH";

    private int refreshColor;

    private Context mContext;

    // 初始状态
    private static final int INIT = 0;
    // 释放刷新
    private static final int RELEASE_TO_REFRESH = 1;
    // 正在刷新
    private static final int REFRESHING = 2;
    // 操作完毕
    private static final int DONE = 3;
    // 当前状态
    private int state = INIT;

    /**
     * 头部刷新布局
     */
    private View headRefreshLayout;

    /**
     * 刷新文字
     */
    private TextView tvRefresh;

    /**
     * 下拉显示箭头
     */
    private ImageView ivRefresh;
    /**
     * 刷新显示的bar
     */
    private ProgressBar mProgressBar;

    /**
     * 刷新头部高度
     */
    private int headHeight;

    /**
     * 下拉的距离Y
     */
    private float mTouchDownY = -1;

    /**
     * 最小移动距离，用于判断是否在下拉
     */
    private final static float MIN_MOVE_DISTANCE = 5.0f;

    private final static float PULL_DOWN_DISTANCE = 300f;


    private boolean isRefresh = false, isLoad = false;

    private RefreshListener refreshListener;


    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        initHeadView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout, defStyleAttr, 0);
        refreshColor = typedArray.getColor(R.styleable.RefreshLayout_refreshColor, Color.WHITE);
        typedArray.recycle();
    }

    private void initHeadView() {
        headRefreshLayout = LayoutInflater.from(mContext).inflate(R.layout.head_refresh_layout, null);
        tvRefresh = (TextView) headRefreshLayout.findViewById(R.id.tv_refresh);
        ivRefresh = (ImageView) headRefreshLayout.findViewById(R.id.iv_refresh);
        mProgressBar = (ProgressBar) headRefreshLayout.findViewById(R.id.progress_refresh);
//        LinearLayout.LayoutParams headLayoutParams = (LinearLayout.LayoutParams) headRefreshLayout.getLayoutParams();
//        headHeight = -headRefreshLayout.getHeight();
//        headLayoutParams.topMargin = headHeight;
//        headRefreshLayout.setLayoutParams(headLayoutParams);
        addView(headRefreshLayout);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!canScroll()) {
            return super.dispatchTouchEvent(ev);
        }
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mTouchDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float m = ev.getRawY() - mTouchDownY;
                if (m > MIN_MOVE_DISTANCE && canScroll()) {
                    if (ev.getRawY() - mTouchDownY > PULL_DOWN_DISTANCE) {
                        changeState(RELEASE_TO_REFRESH);
                    } else {
                        changeState(INIT);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (state == RELEASE_TO_REFRESH) {
                    changeState(REFRESHING);
                }
                // 重置
                mTouchDownY = -1;
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 改变状态
     *
     * @param state
     */
    private void changeState(int state) {
        this.state = state;
        switch (state) {
            case INIT://初始化状态
                isRefresh = false;
                tvRefresh.setText("下拉刷新");
                mProgressBar.setVisibility(GONE);
                ivRefresh.setVisibility(VISIBLE);
                ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_down));
                break;
            case RELEASE_TO_REFRESH://释放刷新状态
                isRefresh = true;
                tvRefresh.setText("释放立即刷新");
                mProgressBar.setVisibility(GONE);
                ivRefresh.setVisibility(VISIBLE);
                ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_up));
                break;
            case REFRESHING://正在刷新状态
                tvRefresh.setText("正在刷新");
                mProgressBar.setVisibility(VISIBLE);
                ivRefresh.setVisibility(GONE);
                if (null != refreshListener) refreshListener.onRefresh();
                break;
            case DONE://刷新完成状态
                tvRefresh.setText("刷新完成");
                mProgressBar.setVisibility(GONE);
                isRefresh = false;
                break;
            default:
                break;
        }
    }

    /**
     * 判断是否可以下拉
     *
     * @return
     */
    private boolean canScroll() {
        View childView;
        if (getChildCount() > 1) {
            childView = this.getChildAt(1);
            if (childView instanceof ScrollView) {
                if (((ScrollView) childView).getScrollY() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean isRefreshing() {
        if (isRefresh) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLoading() {
        if (isLoad) {
            return true;
        } else {
            return false;
        }
    }


    public void setRefreshFinish() {
        changeState(DONE);
    }

    public void setLoadFinish() {
        isLoad = false;
    }

    public interface RefreshListener {
        void onRefresh();
    }
}

