package com.lzw.pinnedhead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/18
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  IndexBar
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public final class IndexBar extends View {
        //用于显示的字母
        public String[] words = {"#", "热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"
                  , "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        private Paint paint;//用于绘制的画笔
        private int cellHeight;//字母所在表格的高度
        private int cellWidth;//字母所在表格的宽度
        private OnIndexBarChangeListener onIndexBarChangeListener;//索引变化的监听
        private Context context;

        public void setOnIndexBarChangeListener(OnIndexBarChangeListener onIndexBarChangeListener) {
                this.onIndexBarChangeListener = onIndexBarChangeListener;
        }

        public void setWords(String[] words) {
                this.words = words;
                postInvalidate();
        }

        public IndexBar(Context context) {
                this(context, null);
        }

        public IndexBar(Context context, AttributeSet attrs) {
                this(context, attrs, 0);
        }

        public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                this.context = context;
                init();
        }

        /*
         * 初始化数据
         */
        private void init() {
                paint = new Paint();
                paint.setAntiAlias(true);//设置抗锯齿
                paint.setTextSize(30);
                //paint.setColor(Color.BLUE);
                //paint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
        }

        /**
         * 在这里把字母画出来
         *
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
                if (cellHeight == 0)
                        cellHeight = getMeasuredHeight() / words.length;//初始化格子的高度,为整个控件高度的1/26
                if (cellWidth == 0) cellWidth = getMeasuredWidth();//表格的宽度为控件的宽度
                for (int i = 0; i < words.length; i++) {
                        String word = words[i];//获取到当前要绘制的字母
                        float x = 0;//绘制字体的X 起点,每个都一样, 刚好是整个控件宽度的一半减去字体宽度的一半
                        float y = 0;//每个字都不一样, 刚好是它所处的索引乘以格子高度 加上 格子高度的一半再加上字体高度的一半
                        x = cellWidth / 2 - paint.measureText(word) / 2;//measureText返回指定字符串的宽度
                        Rect rect = new Rect();//创建一个矩形
                        paint.getTextBounds(word, 0, word.length(), rect);//返回与这个字符串有关的属性, 宽高度都有
                        y = i * cellHeight + cellHeight / 2 + rect.height() / 2;
                        paint.setColor(Color.parseColor(lastIndex == i ? "#4FC3F3" : "#666666"));// 如果当前绘制的字母刚好是点击或者是滑动到的字母 就改变颜色
                        canvas.drawText(word, x, y, paint);
                }
        }

        private int lastIndex = -1;// 用于记录上次点击或者滑动的位置

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN://按下去
                        case MotionEvent.ACTION_MOVE://滑动

                                int y = (int) event.getY();//获取当前的Y轴坐标
                                int index = y / cellHeight;//获取到当前点击的区域相对于字母是什么索引
                                if (index >= 0) {
                                        if (lastIndex != index && index < words.length) {// 两次的索引不一样,并且当前触摸区域计算出来的索引小宇字母的集合
                                                String word = words[index];
                                                lastIndex = index;
                                                if (onIndexBarChangeListener != null) {
                                                        onIndexBarChangeListener.onIndexChange(index, word);
                                                }
                                        }
                                }
                                break;
                        case MotionEvent.ACTION_UP://抬起
                                lastIndex = -1;//抬起的时候恢复初始化
                                break;

                }
                invalidate();//刷新界面
                return true;
        }

        public interface OnIndexBarChangeListener {
                void onIndexChange(int index, String word);
        }
}
