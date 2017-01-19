package com.mb.mubai.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by lzw on 2016/12/20.
 * 自定义GridView，解决Scrollview中嵌套GridView显示不正常的问题
 */
public final class MyGridView extends GridView {

        public MyGridView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        public MyGridView(Context context) {
                super(context);
        }

        public MyGridView(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                          MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, expandSpec);
        }

        @Override

        //GridView禁止滑动
        public boolean dispatchTouchEvent(MotionEvent ev) {

                if (ev.getAction() == MotionEvent.ACTION_MOVE) {

                        return true;  //禁止GridView滑动
                }
                return super.dispatchTouchEvent(ev);
        }
}
