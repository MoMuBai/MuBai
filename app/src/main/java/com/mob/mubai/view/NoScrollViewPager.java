package com.mob.mubai.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by lzw on 2016/12/20.
 * 不允许滑动的ViewPager
 */
public final class NoScrollViewPager extends ViewPager {
        public NoScrollViewPager(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        private boolean noScroll = true;

        public NoScrollViewPager(Context context) {
                super(context);
        }

        public void setNoScroll(boolean noScroll) {
                this.noScroll = noScroll;
        }

        @Override
        public void scrollTo(int x, int y) {
                super.scrollTo(x, y);
        }

        @Override
        public boolean onTouchEvent(MotionEvent arg0) {
                if (noScroll)
                        return false;
                else
                        return super.onTouchEvent(arg0);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent arg0) {
                if (noScroll)
                        return false;
                else
                        return super.onInterceptTouchEvent(arg0);
        }

        @Override
        public void setCurrentItem(int item, boolean smoothScroll) {
                super.setCurrentItem(item, smoothScroll);
        }

        @Override
        public void setCurrentItem(int item) {
                super.setCurrentItem(item);
        }
}
