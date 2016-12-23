package com.example.mglide;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzw on 2016/12/23.
 */

public class MGlideHelper extends RelativeLayout {

        private ViewPager mViewPager;

        private String content;

        private int contentColor;

        private Float contentSize;

        private List<ImageView> viewList;

        private TextView tvContent;


        public MGlideHelper(Context context) {
                this(context, null);
        }

        public MGlideHelper(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public MGlideHelper(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
                viewList = new ArrayList<>();
                LayoutInflater.from(context).inflate(R.layout.activity_mglide, this);
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MGlideHelperLayout);
                content = typedArray.getString(R.styleable.MGlideHelperLayout_content);
                contentColor = typedArray.getInteger(R.styleable.MGlideHelperLayout_content_color, Color.BLACK);
                contentSize = typedArray.getFloat(R.styleable.MGlideHelperLayout_content_size, 10f);
                typedArray.recycle();
        }

        @Override
        protected void onFinishInflate() {
                super.onFinishInflate();
                mViewPager = (ViewPager) findViewById(R.id.view_pager);
        }

        /**
         * 设置背景ViewList
         */
        public void setBgView(List<ImageView> viewList) {
                this.viewList = viewList;
                mViewPager.setAdapter(new MyAdapter());
                postInvalidate();
        }

        /**
         * 设置内容
         */
        public void setContent(String content) {

        }


        /**
         * 设置内容颜色
         */
        public void setContentColor(int color) {

        }


        /**
         * 设置内容大小
         */
        public void setContentSize(Float size) {

        }


        /**
         * 设置是在顶部的内容
         */
        public boolean setTopContent() {
                return true;
        }

        /**
         * 设置是在底部的内容
         */
        public boolean setBottomContent() {
                return true;
        }


        /**
         * 设置是否有圆点显示
         */
        public boolean setDotView() {
                return true;
        }


        /**
         * 设置是否有圆点显示
         */
        public boolean disDotView() {
                return false;
        }


        class MyAdapter extends PagerAdapter {

                public MyAdapter() {

                }

                @Override
                public int getCount() {
                        return viewList.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                        return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                        ImageView imageView = viewList.get(position);
                        container.addView(imageView);
                        return container;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                        //  super.destroyItem(container, position, object);
                        ImageView imageView = viewList.get(position);
                        container.removeView(imageView);
                }
        }
}
