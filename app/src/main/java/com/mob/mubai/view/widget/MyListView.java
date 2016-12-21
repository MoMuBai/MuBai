package com.mob.mubai.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by lzw on 2016/12/20.
 */
public final class MyListView extends ListView {

        public MyListView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        public MyListView(Context context) {
                super(context);
        }

        public MyListView(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                          MeasureSpec.AT_MOST);
                super.onMeasure(widthMeasureSpec, expandSpec);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {

                if (ev.getAction() == MotionEvent.ACTION_MOVE) {

                        return true;
                }
                return super.dispatchTouchEvent(ev);
        }
}
