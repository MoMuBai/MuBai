package com.mb.mubai.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author: lzw
 * @date: 2018/1/29 下午4:20
 * @desc:
 */

public class MyViewGroup extends ViewGroup {

    private int textCount = 2;


    private Context mContext;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyViewGroup", "dispatchTouchEvent:" + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MyViewGroup", "onInterceptTouchEvent:" + ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyViewGroup", "onTouchEvent:" + event);
        return super.onTouchEvent(event);
    }
}
