package com.mubai.refresh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
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

    private static final String TAG = "REFRESH";

    private int refreshColor;

    private Context mContext;

    private enum RefreshStatus {
        REFRESH_DOWN, REFRESHING, LOADING, LOAD_UP
    }

    /**
     * 下拉的距离Y
     */
    private float mTouchDownY = -1;

    private boolean isRefresh = false, isLoad = false;

    private RefreshListener refreshListener;

    private LoadListener loadListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    public void setLoadListener(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout, defStyleAttr, 0);
        refreshColor = typedArray.getColor(R.styleable.RefreshLayout_refreshColor, Color.WHITE);
        typedArray.recycle();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isRefreshing()) {
            return true;
        }
        if (isLoading()) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchDownY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (mTouchDownY < event.getRawY()) {
                    if (event.getRawY() - mTouchDownY > 300f) {
                        isRefresh = true;
                        Toast.makeText(mContext, "正在刷新", Toast.LENGTH_SHORT).show();
                        refreshListener.onRefresh();
                    } else {
                        isRefresh = false;
                        Toast.makeText(mContext, "释放停止", Toast.LENGTH_SHORT).show();
                    }
                }
                if (mTouchDownY > event.getRawY()) {
                    if (mTouchDownY - event.getRawY() > 300f) {
                        isLoad = true;
                        loadListener.onLoad();
                        Toast.makeText(mContext, "上拉加载", Toast.LENGTH_SHORT).show();
                    } else {
                        isLoad = false;
                        Toast.makeText(mContext, "释放停止", Toast.LENGTH_SHORT).show();
                    }
                }
                // 重置
                mTouchDownY = -1;
                break;
        }
        return true;
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
        isRefresh = false;
    }

    public void setLoadFinish() {
        isLoad = false;
    }

    public interface RefreshListener {
        void onRefresh();
    }

    public interface LoadListener {
        void onLoad();
    }
}

