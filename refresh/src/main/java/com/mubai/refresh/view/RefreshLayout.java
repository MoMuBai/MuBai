package com.mubai.refresh.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: lzw
 * @date: 2018/2/1 下午2:00
 * @desc:
 */


public class RefreshLayout extends LinearLayout {

    private static final String TAG = "REFRESH_LAYOUT";

    /**
     * 刷新的颜色
     */
    private int refreshColor;

    private Context mContext;

    /**
     * 初始状态
     */
    private static final int INIT = 0;
    /**
     * 下拉刷新
     */
    private static final int PULL_TO_REFRESH = 1;
    /**
     * 释放刷新
     */
    private static final int RELEASE_TO_REFRESH = 2;
    /**
     * 正在刷新
     */
    private static final int REFRESHING = 3;

    @Status
    private int status;

    private int mTouchDownY;

    private int moveY, loadMoveY;

    /**
     * 头部刷新布局
     */
    private View refreshView;

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
     * 最小移动距离，用于判断是否在下拉
     */
    private final static float MIN_MOVE_DISTANCE = 5.0f;


    private RefreshListener refreshListener;

    private View contentView, footView;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({INIT, PULL_TO_REFRESH, RELEASE_TO_REFRESH, REFRESHING})
    public @interface Status {

    }

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
        initFootView();
    }


    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        setOrientation(LinearLayout.VERTICAL);
    }

    public RefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RefreshLayout, defStyleAttr, 0);
        refreshColor = typedArray.getColor(R.styleable.RefreshLayout_refreshColor, Color.WHITE);
        typedArray.recycle();
        initHeadView();
    }


    /**
     * 初始化HeadView
     */
    private void initHeadView() {
        refreshView = LayoutInflater.from(mContext).inflate(R.layout.head_refresh_layout, null);
        tvRefresh = (TextView) refreshView.findViewById(R.id.tv_refresh);
        ivRefresh = (ImageView) refreshView.findViewById(R.id.iv_refresh);
        relativeLayout = (RelativeLayout) refreshView.findViewById(R.id.refresh_layout);
        mProgressBar = (ProgressBar) refreshView.findViewById(R.id.progress_refresh);
        addView(refreshView);
        refreshView.setVisibility(GONE);
    }

    /**
     * 初始化FootView
     */
    private void initFootView() {
        footView = LayoutInflater.from(mContext).inflate(R.layout.foot_refresh_layout, null);
        addView(footView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(1);
        contentView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int y = (int) ev.getRawY();
        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                mTouchDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int m = y - mTouchDownY;
                if (m > MIN_MOVE_DISTANCE && canRefresh()) {
                    /**
                     * 当下拉大于最小距离且子View可以滑动的时候将事件拦截下来
                     */
                    return true;
                }
//                if (m < 0 && canLoad()) {
//                    return true;
//                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int y = (int) event.getRawY();
        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                mTouchDownY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int m = y - mTouchDownY;
                /**
                 * 执行偏移
                 */
                refreshMoveEvent(m);
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                int n = y - mTouchDownY;
                Log.d(TAG, "n:" + n);
                /**
                 * 执行刷新
                 */
                refreshEvent(n);
            default:
                break;
        }
        return true;
    }

    private void loadMoveEvent(int m) {
        Log.d(TAG, "m:" + m);
        LayoutParams layoutParams = (LayoutParams) footView.getLayoutParams();
        layoutParams.bottomMargin = (int) (m * 0.3f);
        Log.d(TAG, "layoutParams.bottomMargin:" + layoutParams.bottomMargin);
        footView.setLayoutParams(layoutParams);
        footView.setVisibility(VISIBLE);
        loadMoveY = layoutParams.bottomMargin - refreshView.getHeight();
        Log.d(TAG, "contentView.getHeight():" + contentView.getHeight());
        this.scrollTo(0, loadMoveY);
        Log.d(TAG, "loadMoveY:" + loadMoveY);
    }

    private void loadEvent() {
        LinearLayout.LayoutParams lp = (LayoutParams) this.footView.getLayoutParams();
        Log.d(TAG, "lp.bottomMargin:" + lp.bottomMargin);
        Log.d(TAG, "开始加载");
    }

    private void refreshEvent(int n) {
        if (n > 0) {
            LinearLayout.LayoutParams lp = (LayoutParams) this.refreshView.getLayoutParams();
            Log.d(TAG, "lp.topMargin:" + lp.topMargin);
            Log.d(TAG, "开始刷新");
            if (lp.topMargin > 100) {
                lp.topMargin = lp.topMargin - moveY - refreshView.getHeight();
                refreshView.setLayoutParams(lp);
                tvRefresh.setText("正在刷新");
                ivRefresh.setVisibility(GONE);
                mProgressBar.setVisibility(VISIBLE);
                refreshListener.onRefresh();
            } else {
                refreshView.setVisibility(GONE);
            }
        } else {
            refreshView.setVisibility(GONE);
        }
    }

    /**
     * 执行偏移
     *
     * @param m
     */
    private void refreshMoveEvent(int m) {
        Log.d(TAG, "m:" + m);
        status = PULL_TO_REFRESH;
        LayoutParams layoutParams = (LayoutParams) refreshView.getLayoutParams();
        layoutParams.topMargin = (int) (m * 0.3f);
        Log.d(TAG, "layoutParams.topMargin:" + layoutParams.topMargin);
        if (layoutParams.topMargin > 100) {
            tvRefresh.setText("释放立即刷新");
            ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_up));
        } else {
            tvRefresh.setText("下拉刷新");
            ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_down));
        }
        refreshView.setLayoutParams(layoutParams);
        refreshView.setVisibility(VISIBLE);
        moveY = layoutParams.topMargin - refreshView.getHeight();
        Log.d(TAG, "moveY:" + moveY);
    }

    /**
     * 判断是否可以下拉
     *
     * @return
     */
    private boolean canRefresh() {
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
                int top = ((ListView) childView).getChildAt(0).getTop();
                int pad = ((ListView) childView).getListPaddingTop();
                if ((Math.abs(top - pad)) < 3
                        && ((ListView) childView).getFirstVisiblePosition() == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否可以加载
     *
     * @return
     */
    private boolean canLoad() {
        View childView;
        if (getChildCount() > 1) {
            childView = this.getChildAt(1);
            if (childView instanceof ScrollView) {
                if (((ScrollView) childView).getChildAt(0).getHeight() - ((ScrollView) childView).getHeight() == ((ScrollView) childView).getScrollY()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 刷新完成
     */
    public void setRefreshFinish() {
        refreshView.setVisibility(GONE);
        tvRefresh.setText("下拉刷新");
        ivRefresh.setVisibility(VISIBLE);
        ivRefresh.setImageDrawable(mContext.getResources().getDrawable(R.drawable.refresh_down));
        mProgressBar.setVisibility(GONE);
    }


    public interface RefreshListener {
        /**
         * 执行刷新
         */
        void onRefresh();

        /**
         * 执行加载
         */
        void onLoad();
    }
}

