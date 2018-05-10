package com.mfyk.mfdemotest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

/**
 * 获取屏幕长宽之类的东西
 */
public class DensityUtils {

    /**
     * 获取屏幕尺寸
     *
     * @param context
     */
    public static DisplayMetrics getWindowSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
//        if (Build.VERSION.SDK_INT >= 17) {
//            display.getRealMetrics(metrics);//获取屏幕尺寸高度，包括navigationBar
//        } else {
        display.getMetrics(metrics);
//        }
        return metrics;
    }

    /**
     * 获取全部屏幕尺寸
     *
     * @param context
     */
    public static DisplayMetrics getAllWindowSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
//        if (Build.VERSION.SDK_INT >= 17) {
//            display.getRealMetrics(metrics);//获取屏幕尺寸高度，包括navigationBar
//        } else {
        display.getMetrics(metrics);
//        }
        return metrics;
    }

    /**
     * 获取屏幕宽---px
     *
     * @param context
     * @return
     */
    public static int getPxWidth(Context context) {
        return getWindowSize(context).widthPixels;
    }

    /**
     * 获取屏幕高---px
     *
     * @param context
     * @return
     */
    public static int getPxHeight(Context context) {
        return getWindowSize(context).heightPixels;
    }

    /**
     * 获取纯屏幕高---px 包括navigationBar
     *
     * @param context
     * @return
     */
    public static int getAllPxHeight(Context context) {
        return getAllWindowSize(context).heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int dp2sp(Context context, float dipValue) {
        float pxValue = dp2px(context, dipValue);
        return px2sp(context, pxValue);
    }

    public static int sp2dp(Context context, float spValue) {
        float pxValue = sp2px(context, spValue);
        return px2dp(context, pxValue);
    }

    /**
     * px to dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f * (pxValue >= 0 ? 1 : -1));
    }

    /**
     * dp to px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dp2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f * (dipValue >= 0 ? 1 : -1));
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px to sp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp to px
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取状态栏高
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        Rect r = new Rect();
        v.getWindowVisibleDisplayFrame(r);
        return r.top;
    }

    @SuppressLint("NewApi")
    public static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }


    public static int getNavigationBarHeight(Activity activity) {
        if (!checkDeviceHasNavigationBar(activity)) {
            return 0;
        }
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId);
    }
}
