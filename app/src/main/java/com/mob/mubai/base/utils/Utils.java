package com.mob.mubai.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by lzw on 2015/7/25.
 */
public class Utils {


        /**
         * dp转px
         *
         * @param context
         * @param dp
         * @return
         */
        public static int dp2px(Context context, float dp) {
                return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                          dp, context.getResources().getDisplayMetrics());
        }

        /**
         * 获取屏幕宽度
         *
         * @param context
         * @return
         */
        public static int getScreenWidth(Context context) {
                WindowManager wm = (WindowManager) context
                          .getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics outMetrics = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(outMetrics);
                return outMetrics.widthPixels;
        }
}
