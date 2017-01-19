package com.lzw.library.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;

public class SpannableStringUtil {

	private static final String TYPEFACE_DEFAULT = "default";
	private static final String TYPEFACE_DEFAULT_BOLD = "default-bold";
	private static final String TYPEFACE_MONOSPACE = "monospace";
	private static final String TYPEFACE_SERIF = "serif";
	private static final String TYPEFACE_SANS_SERIF = "sans-serif";
	private static final int FLAGS = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

	/**
	 * 设置字体样式
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param mode
	 * @return
	 */
	public static SpannableString setTypeFace(CharSequence source, int start,
						  int end, TypeFaceMode mode) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new TypefaceSpan(getTypeFaceMode(mode)), start, end, FLAGS);
		return sp;
	}

	private static String getTypeFaceMode(TypeFaceMode mode) {
		switch (mode) {
		case DEFAULT:

			return TYPEFACE_DEFAULT;

		case DEFAULT_BOLD:

			return TYPEFACE_DEFAULT_BOLD;

		case MONOSPACE:

			return TYPEFACE_MONOSPACE;

		case SERIF:

			return TYPEFACE_SERIF;

		case SANS_SERIF:

			return TYPEFACE_SANS_SERIF;
		}
		return TYPEFACE_DEFAULT;
	}

	public enum TypeFaceMode {
		DEFAULT, DEFAULT_BOLD, MONOSPACE, SERIF, SANS_SERIF;
	}

	/**
	 * 设置字体大小(绝对值)
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param size
	 * @param isDipSize
	 * @return
	 */
	public static SpannableString setAbsTextSize(CharSequence source,
						     int start, int end, int size, boolean isDipSize) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new AbsoluteSizeSpan(size, isDipSize), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体大小(相对值)
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param scale
	 * @return
	 */
	public static SpannableString setRelaTextSize(CharSequence source,
						      int start, int end, float scale) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new RelativeSizeSpan(scale), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体颜色
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param color
	 * @return
	 */
	public static SpannableString setTextColor(CharSequence source, int start,
						   int end, int color) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new ForegroundColorSpan(color), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体背景色
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param color
	 * @return
	 */
	public static SpannableString setTextBackgroundColor(CharSequence source,
							     int start, int end, int color) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new BackgroundColorSpan(color), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体样式(正常、粗体、斜体、粗斜体)
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @param style
	 *            Typeface.NORMAL,Typeface.BOLD,
	 *            Typeface.ITALIC,Typeface.BOLD_ITALIC
	 * @return
	 */
	public static SpannableString setStyle(CharSequence source, int start,
					       int end, int style) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new StyleSpan(style), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体下划线
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static SpannableString setUnderline(CharSequence source, int start,
						   int end) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new UnderlineSpan(), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体删除线
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static SpannableString setStrikethrough(CharSequence source,
						       int start, int end) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new StrikethroughSpan(), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体下标
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static SpannableString setSubscript(CharSequence source, int start,
						   int end) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new SubscriptSpan(), start, end, FLAGS);
		return sp;
	}

	/**
	 * 设置字体上标
	 * 
	 * @param source
	 * @param start
	 * @param end
	 * @return
	 */
	public static SpannableString setSuperscript(CharSequence source,
						     int start, int end) {
		if (source == null) {
			return null;
		}
		SpannableString sp = new SpannableString(source);
		sp.setSpan(new SuperscriptSpan(), start, end, FLAGS);
		return sp;
	}
}
