package com.lzw.ys7.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.lzw.ys7.R;

import org.w3c.dom.Text;

/**
 * @author: lzw
 * @date: 22/12/2017 10:41 AM
 * @desc:
 */

public class TextHelper {
    private static TextHelper mTextHelper;
    private Context mContext;
    private TextView mTextView;

    private TextHelper(Context context) {
        this.mContext = context;
    }

    public static TextHelper getTextHelper(Context context) {
        if (null == mTextHelper) {
            mTextHelper = new TextHelper(context);
        }
        return mTextHelper;
    }

    /**
     * 设置TextView的大小和颜色
     *
     * @param textView
     */
    @SuppressLint("NewApi")
    public void setText(TextView textView) {
        this.mTextView = textView;
        this.mTextView.setTextSize(20f);
        this.mTextView.setTextColor(mContext.getColor(R.color.black));
    }
}
