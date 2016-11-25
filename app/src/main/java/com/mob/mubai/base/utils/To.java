package com.mob.mubai.base.utils;

import android.widget.Toast;

import com.mob.mubai.App;


/**
 * Created by lzw on 2016/11/8.
 * 防止连续弹出Toast
 */
public final class To {


    private static boolean isToast = true;

    private static long oneTime=0;

    private static long twoTime=0;

    private static Toast toast = null;

    private static String oldMsg;


    public static void d(String msg){
        if (isToast) {
            if (toast == null) {
                toast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (msg.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = msg;
                    toast.setText(msg);
                    toast.show();
                }
            }
            oneTime = twoTime;
        }
    }

}
