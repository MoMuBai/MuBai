package com.mb.mubai.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mb.mubai.R;
import com.mb.mubai.data.bean.BrokenLineCusVisit;

import java.util.List;

/**
 * Author: lzw
 * Date: 2018/9/10
 * Description: This is BrokenLineCusVisitView
 */

public class BrokenLineCusVisitView extends View {

    private int width;
    private int heigh;

    //网格的宽度与高度
    private int gridspace_width;
    private int gridspace_heigh;

    //底部空白的高度
    private int brokenline_bottom;

    //灰色背景的画笔
    private Paint mPaint_bg;
    //灰色网格的画笔
    private Paint mPaint_gridline;
    //文本数据的画笔
    private Paint mPaint_text;

    //折线圆点的蓝色背景
    private Paint mPaint_point_bg;
    //折线圆点的白色表面
    private Paint mPaint_point_sur;
    //阴影路径的画笔
    private Paint mPaint_path;
    //折线的画笔
    private Paint mPaint_brokenline;
    //路径
    private Path mpath = new Path();
    //客户拜访的折线（BrokenLineCusVisit）数据
    private List<BrokenLineCusVisit> mdata;


    public BrokenLineCusVisitView(Context context) {
        super(context);
    }

    public BrokenLineCusVisitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inite(context);
    }

    private void inite(Context context) {

        mPaint_bg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_bg.setColor(getResources().getColor(R.color.white));

        mPaint_gridline = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_gridline.setColor(Color.argb(0xff, 0xce, 0xCB, 0xce));

        mPaint_brokenline = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_brokenline.setColor(Color.argb(0xff, 0x91, 0xC8, 0xD6));
        mPaint_brokenline.setTextSize(18);
        mPaint_brokenline.setTextAlign(Paint.Align.CENTER);

        mPaint_point_bg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_point_bg.setColor(Color.argb(0xff, 0x91, 0xC8, 0xD6));
        //注意path的画笔的透明度已经改变了
        mPaint_path = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_path.setColor(Color.argb(0x33, 0x91, 0xC8, 0xD6));


        mPaint_point_sur = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_point_sur.setColor(Color.WHITE);


        mPaint_text = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_text.setColor(Color.BLACK);
        mPaint_text.setTextAlign(Paint.Align.CENTER);

        invalidate();
    }

    //data的set/get方法，用于设置数据
    public List<BrokenLineCusVisit> getMdata() {
        return mdata;
    }

    public void setMdata(List<BrokenLineCusVisit> mdata) {
        this.mdata = mdata;
        requestLayout();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制折线图的白色背景
        canvas.drawColor(Color.WHITE);
        //绘制灰色矩形区域
        canvas.drawRect(10, 0, width, heigh - brokenline_bottom, mPaint_bg);
        //绘制网格线，横向的；Y轴不变 X轴绘制直线
        for (int j = 0; j < 6; j++) {
            canvas.drawLine(10, gridspace_heigh * (j + 1), width, gridspace_heigh * (j + 1), mPaint_gridline);
        }
        float startX = 10, stopX, startY = gridspace_heigh, stopY;
        stopX = gridspace_width * 7 + 10;
        stopY = heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(0).getData() / 100;

        for (int i = 0; i < mdata.size(); i++) {
            if (i == 0) {
                //开始时需要将path移动到要开始绘制的位置，否则默认从（0,0）开始，绘制path路径
                mpath.moveTo(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100);
            }
            //绘制纵向网格线
//            canvas.drawLine(gridspace_width * i + 10, 0, gridspace_width * i + 10, heigh - brokenline_bottom, mPaint_gridline);
            if (i != mdata.size() - 1) {
                //根据圆点位置绘制折线
                canvas.drawLine(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100, gridspace_width * (i + 1) + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i + 1).getData() / 100, mPaint_brokenline);
                //path的路径跟绘制的线的路径是相同的，因此path的起止点与线的起止点相同
                mpath.quadTo(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100, gridspace_width * (i + 1) + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i + 1).getData() / 100);
            }
            //绘制圆点，圆点位置根据网格线的位置确定
            canvas.drawCircle(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100, 10, mPaint_point_bg);
            canvas.drawCircle(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100, 5, mPaint_point_sur);
            //绘制数据的数量
            String data = mdata.get(i).getData() + "";
            canvas.drawText(data, gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100 - mPaint_brokenline.measureText(data), mPaint_brokenline);
            //绘制底部空白处：数据的日期
            String date = mdata.get(i).getDate();
            canvas.drawText(date, gridspace_width * i + 10, heigh - brokenline_bottom / 2, mPaint_text);
            if (i == mdata.size() - 1) {
                //绘制完最后一个点，path需要沿着纵轴向下到达heigh - brokenline_bottom 的位置再水平到达（10，heigh - brokenline_bottom ）的位置，最后闭合
                mpath.quadTo(gridspace_width * i + 10, heigh - brokenline_bottom - (heigh - brokenline_bottom) * mdata.get(i).getData() / 100, gridspace_width * i + 10, heigh - brokenline_bottom);
                mpath.quadTo(gridspace_width * i + 10, heigh - brokenline_bottom, 10, heigh - brokenline_bottom);
                mpath.close();
            }
        }
        Log.d("BrokenLineCusVisitView", "startX:" + startX);
        Log.d("BrokenLineCusVisitView", "startY:" + startY);
        Log.d("BrokenLineCusVisitView", "stopX:" + stopX);
        Log.d("BrokenLineCusVisitView", "stopY:" + stopY);
        LinearGradient linearGradient = new LinearGradient(startY, stopX, startX, stopY, new int[]{getResources().getColor(R.color.white), getResources().getColor(R.color.white), getResources().getColor(R.color.green1), getResources().getColor(R.color.green2), getResources().getColor(R.color.green3)}, null, Shader.TileMode.CLAMP);
        //最终在画布上绘制path
        mPaint_path.setShader(linearGradient);
        canvas.drawPath(mpath, mPaint_path);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        gridspace_width = 100;
        if (mdata.size() == 0) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        } else {
            //根据数据的条数设置宽度
//            width = gridspace_width * mdata.size() + 10;
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }

        heigh = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        brokenline_bottom = 50;
        Log.d("bottm", "" + brokenline_bottom);
        gridspace_heigh = (heigh - brokenline_bottom) / 6;
        setMeasuredDimension(width, heigh);
    }
}
