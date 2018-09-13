package com.mb.mubai.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mb.mubai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lzw
 * Date: 2018/9/10
 * Description: This is PulseView
 */

public class PulseView extends View {

    private int XLength;
    private int XPoint;
    private int XScale = getResources().getDimensionPixelSize(R.dimen.x2);
    private int YLength = getResources().getDimensionPixelSize(R.dimen.x120);
    private int YPoint = getResources().getDimensionPixelSize(R.dimen.x100);
    private int YScale = getResources().getDimensionPixelSize(R.dimen.x2);

    private int MaxDataSize = XLength / XScale;
    private List<Integer> data;
    private int index;

    public PulseView(Context paramContext) {
        this(paramContext, null);
    }

    public PulseView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    @SuppressLint("ResourceType")
    public PulseView(Context context, AttributeSet attrs, int resId) {
        super(context, attrs, resId);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PulseView, resId, 0);
        XScale = typedArray.getDimensionPixelSize(0, getResources().getDimensionPixelSize(R.dimen.x2));
        YScale = typedArray.getDimensionPixelSize(1, getResources().getDimensionPixelSize(R.dimen.x20));

        typedArray.recycle();

        data = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        XPoint = getResources().getDimensionPixelSize(R.dimen.x10);
        YPoint = height - getResources().getDimensionPixelSize(R.dimen.x10);
        XLength = width - getResources().getDimensionPixelSize(R.dimen.x20);
        YLength = height - getResources().getDimensionPixelSize(R.dimen.x20);

        MaxDataSize = (XLength / XScale);
        Log.i("PUlse", MaxDataSize + "");
    }

    public void addDataByPC80B(List<Integer> list) {
        if (data != null && data.size() > 0) {
            data.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            data.add(list.get(i));
            postInvalidate();
            if (data.size() >= MaxDataSize) {
                data.remove(0);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.gray));
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.red));
        //    画y轴
        canvas.drawLine(XPoint, YPoint - YLength, XPoint, YPoint, paint);

        //    画水平格子线
        for (int i = 0; i * YScale <= YLength; i++) {
            canvas.drawLine(XPoint, YPoint - i * YScale, XPoint + XLength, YPoint - i * YScale, paint);
        }
        //    画x轴
        canvas.drawLine(XPoint, YPoint, XPoint + XLength, YPoint, paint);
        //    画竖直格子线
        for (int j = 0; j * YScale <= XLength; j++) {
            canvas.drawLine(XPoint + j * YScale, YPoint, XPoint + j * YScale, YPoint - YLength, paint);
        }
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2.0F);
        if (data.size() > 0) {
            //    画搏动折线
            for (int k = 1; k < data.size(); k++) {
                canvas.drawLine(XPoint + (k - 1) * XScale, YPoint - data.get(k - 1), XPoint + k * XScale, YPoint - data.get(k), paint);
            }
            //    画刷新线
            paint.setColor(getResources().getColor(R.color.gray));
            paint.setStrokeWidth(5.0F);
            canvas.drawLine(index * XScale, YPoint - YLength, index * XScale, YPoint, paint);
        }
    }


    public void setData(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (data.size() <= MaxDataSize) {
                data.add(list.get(i));
            } else {
                if (index == MaxDataSize) {
                    index = 0;
                }
                data.set(index, list.get(i));
                index++;
            }
            postInvalidate();
        }
    }
}
