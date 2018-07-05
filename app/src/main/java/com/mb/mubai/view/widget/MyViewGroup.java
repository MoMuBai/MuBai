package com.mb.mubai.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mb.mubai.R;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 获取ViewGroup父容器为其设置的计算模式和尺寸，大多数情况下，只要不是wrap_context,父容器都能正确的计算其尺寸
         */
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //将所有的子View进行测量，这会触发每个子View的onMeasure函数
        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(withSize,heightSize);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return super.generateLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    public void setTextCount(int count) {
        textCount = count;
        postInvalidate();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (textCount > 3) {
            int height = 0;
            View child1 = getChildAt(0);
            TextView text = new TextView(mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            text.setLayoutParams(params);
            text.layout(0, height, child1.getMeasuredWidth(), height + child1.getMeasuredHeight());
        } else {
            int height = 0;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            TextView text1 = new TextView(mContext);
            text1.setLayoutParams(params);
            text1.setText("测试测试测试测试");
            text1.layout(0, height, text1.getMeasuredWidth(), height + text1.getMeasuredHeight());

            addView(text1);
            height = height + text1.getMeasuredHeight();
            TextView text2 = new TextView(mContext);
            text2.setLayoutParams(params);
            text2.setText("测试测试");
            text2.layout(0, height, text2.getMeasuredWidth(), height + text2.getMeasuredHeight());
            addView(text2);

            ImageView img = new ImageView(mContext);
            img.setLayoutParams(params);
            img.setImageResource(R.mipmap.ic_launcher);
            img.layout(text2.getMeasuredWidth(), height, text1.getMeasuredWidth(), height + img.getMeasuredHeight());
            addView(img);
        }
    }
}
