package com.lzw.library.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TextLinkUtil {

	/**
	 * 添加文本超链接
	 * 
	 * @see {@link #addTextLink(TextView, CharSequence, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, int, int, boolean, OnClickListener)}
	 * @param textView
	 *            文本填充的TextView
	 * @param text
	 *            原始文本内容
	 */
	public static void addTextLink(TextView textView, CharSequence text) {
		addTextLink(textView, text, null);
	}

	/**
	 * 添加文本超链接
	 * 
	 * @see {@link #addTextLink(TextView, CharSequence)}
	 * @see {@link #addTextLink(TextView, CharSequence, int)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, int, int, boolean, OnClickListener)}
	 * @param textView
	 *            文本填充的TextView
	 * @param text
	 *            原始文本内容
	 * @param listener
	 *            超链接单击监听事件
	 */
	public static void addTextLink(TextView textView, CharSequence text,
				       OnClickListener listener) {
		addTextLink(textView, text, -1, 0, text.length(), true, listener);
	}

	/**
	 * 添加文本超链接
	 * 
	 * @see {@link #addTextLink(TextView, CharSequence)}
	 * @see {@link #addTextLink(TextView, CharSequence, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, int, int, boolean, OnClickListener)}
	 * @param textView
	 *            文本填充的TextView
	 * @param text
	 *            原始文本内容
	 * @param color
	 *            超链接文本的颜色
	 */
	public static void addTextLink(TextView textView, CharSequence text,
				       int color) {
		addTextLink(textView, text, color, null);
	}

	/**
	 * 添加文本超链接
	 * 
	 * @see {@link #addTextLink(TextView, CharSequence)}
	 * @see {@link #addTextLink(TextView, CharSequence, OnClickListener)}
	 * @see {@link #addTextLink(TextView, CharSequence, int)}
	 * @see {@link #addTextLink(TextView, CharSequence, int, int, int, boolean, OnClickListener)}
	 * @param textView
	 *            文本填充的TextView
	 * @param text
	 *            原始文本内容
	 * @param color
	 *            超链接文本的颜色
	 * @param listener
	 *            超链接单击监听事件
	 */
	public static void addTextLink(TextView textView, CharSequence text,
				       int color, OnClickListener listener) {
		addTextLink(textView, text, color, 0, text.length(), true, listener);
	}

	/**
	 * 添加文本超链接
	 * <ul>
	 * <li>TextView为空时不做任何操作</li>
	 * <li>文本为空、起始位置或者结束位置不合法时设置当前文本内容</li>
	 * </ul>
	 * 
	 * @param textView
	 *            文本填充的TextView
	 * @param text
	 *            原始文本内容
	 * @param color
	 *            超链接文本的颜色
	 * @param start
	 *            超链接起始位置
	 * @param end
	 *            超链接结束位置
	 * @param showUnderline
	 *            是否显示下划线
	 * @param listener
	 *            超链接单击监听事件
	 */
	public static void addTextLink(TextView textView, CharSequence text,
				       int color, int start, int end, boolean showUnderline,
				       OnClickListener listener) {
		if (textView == null) {
			return;
		}
		if (StringUtil.isEmpty(text + "")) {
			textView.setText(text);
			return;
		}
		if (start < 0 || start > text.length() || start > end
				|| end > text.length()) {
			textView.setText(text);
			return;
		}
		SpannableString sp = new SpannableString(text);
		sp.setSpan(new IntentSpan(listener, showUnderline), start, end,
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		if (color > 0) {
			sp.setSpan(new ForegroundColorSpan(color), start, end,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		textView.setText(sp);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public static class IntentSpan extends ClickableSpan {

		private final OnClickListener mOnClickListener;
		private final boolean mShowUnderline;

		public IntentSpan(OnClickListener listener, boolean showUnderline) {
			this.mOnClickListener = listener;
			this.mShowUnderline = showUnderline;
		}

		public void onClick(View view) {
			if (mOnClickListener != null) {
				mOnClickListener.onClick(view);
			}
		}

		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			ds.setUnderlineText(mShowUnderline);
		}
	}
}
