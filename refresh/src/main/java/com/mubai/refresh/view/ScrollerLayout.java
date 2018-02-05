package com.mubai.refresh.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * @author: lzw
 * @date: 2018/2/3 下午3:40
 * @desc:
 */

public class ScrollerLayout extends LinearLayout {

    private Scroller scroller;

    private Context context;


    public ScrollerLayout(Context context) {
        this(context, null);
    }

    public ScrollerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void start() {
        scroller = new Scroller(context);
        scroller.startScroll(0, 0, -300, -500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        scrollTo(-300, -500);
        invalidate();
    }
}
