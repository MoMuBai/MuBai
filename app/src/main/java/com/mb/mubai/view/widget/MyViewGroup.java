package com.mb.mubai.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author: lzw
 * @date: 2018/1/29 下午4:20
 * @desc:
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        child.layout(l, t, r, b);
    }
}
