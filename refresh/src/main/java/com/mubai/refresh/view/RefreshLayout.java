package com.mubai.refresh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.mubai.refresh.R;
import com.mubai.refresh.activity.TouchEventUtil;

/**
 * @author: lzw
 * //
 * @date: 2017/4/5 上午11:17
 * //
 * @desc: 自定义刷新控件
 */

public class RefreshLayout extends LinearLayout {

    private static final String TAG = "REFRESH_LAYOUT";

    private int refreshColor;

    private Context mContext;

    private static boolean isUpDown = false;

    /**
     * 初始状态
     */
    private static final int INIT = 0;
    /**
     * 释放刷新
     */
    private static final int RELEASE_TO_REFRESH = 1;
    /**
     * 正在刷新
     */
    private static final int REFRESHING = 2;
    /**
     * 操作完毕
     */
    private static final int DONE = 3;
    /**
     * 当前状态
     */
    private int state = INIT;

    /**
     * 头部刷新布局
     */
    private View headRefreshLayout;

    /**
     * 刷新文字
     */
    private TextView tvRefresh;

    private ImageView ivRefresh;

    private RelativeLayout relativeLayout;
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


    private float PULL_DOWN_DISTANCE = 300f;


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
        relativeLayout = (RelativeLayout) headRefreshLayout.findViewById(R.id.refresh_layout);
        mProgressBar = (ProgressBar) headRefreshLayout.findViewById(R.id.progress_refresh);
        addView(headRefreshLayout);
        changeState(DONE);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                mTouchDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float m = ev.getRawY() - mTouchDownY;
                if (m > 0) {
                    if (m > MIN_MOVE_DISTANCE && canScroll()) {
                        if (ev.getRawY() - mTouchDownY > PULL_DOWN_DISTANCE) {
                            /**
                             * 滑动大于一定距离表示可以显示刷新
                             */
                            changeState(RELEASE_TO_REFRESH);
                        } else {
                            /**
                             * 滑动小于一定距离表示不可以显示刷新，需显示初始化状态
                             */
                            changeState(INIT);
                        }
                    }
                } else {
                    isUpDown = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                if (isUpDown) {
                    Log.d(TAG, "return super.dispatchTouchEvent(ev)");
                    return super.dispatchTouchEvent(ev);
                } else {
                    if (state == RELEASE_TO_REFRESH) {
                        changeState(REFRESHING);
                    }
                    // 重置
                    mTouchDownY = -1;
                }
                break;
            default:
                break;
        }
        Log.d(TAG, "return true");
        return true;
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
                relativeLayout.setVisibility(VISIBLE);
                break;
            case RELEASE_TO_REFRESH://释放刷新状态
                isRefresh = true;
                tvRefresh.setText("释放立即刷新");
                tvRefresh.setVisibility(VISIBLE);
                mProgressBar.setVisibility(GONE);
                ivRefresh.setVisibility(VISIBLE);
                ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_up));
                relativeLayout.setVisibility(VISIBLE);
                break;
            case REFRESHING://正在刷新状态
                tvRefresh.setText("正在刷新");
                mProgressBar.setVisibility(VISIBLE);
                ivRefresh.setVisibility(GONE);
                if (null != refreshListener) {
                    refreshListener.onRefresh();
                }
                relativeLayout.setVisibility(VISIBLE);
                break;
            case DONE://刷新完成状态
                tvRefresh.setVisibility(VISIBLE);
                ivRefresh.setVisibility(VISIBLE);
                mProgressBar.setVisibility(VISIBLE);
                relativeLayout.setVisibility(GONE);
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
            } else if (childView instanceof ListView) {
            }
        }
        return false;
    }


    public void setPullDownDistance(int distance) {
        setPullDownDistance((float) distance);
    }

    public void setPullDownDistance(float distance) {
        this.PULL_DOWN_DISTANCE = distance;
    }


    public boolean isRefreshing() {
        if (isRefresh) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isLoading() {
        if (isLoad) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 刷新完成
     */
    public void setRefreshFinish() {
        isRefresh = false;
        changeState(DONE);
    }

    public void setLoadFinish() {
        isLoad = false;
    }

    public interface RefreshListener {
        /**
         * 执行刷新
         */
        void onRefresh();
    }
}

