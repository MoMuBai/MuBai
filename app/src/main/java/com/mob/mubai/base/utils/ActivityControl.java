package com.mob.mubai.base.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by mubai on 2016/11/8.
 */

public class ActivityControl {

    private static Stack<Activity> activityStack;


    /**
     * 添加Activity进栈
     *
     * */
    public static void addActivity(Activity activity){
        if (null != activityStack){
            activityStack.add(activity);
        }else {
            activityStack = new Stack<>();
            activityStack.add(activity);
        }
    }

    /**
     * 移除Activity出栈
     *
     * */
    public static void removeActivity(Activity activity){
        if (null != activityStack && activityStack.size()>0){
            activityStack.remove(activity);
        }
    }

    /**
     * 结束所有的Activity
     *
     * */
    public static void finishAllActivity(Activity activity){
        if (null != activityStack && activityStack.size()>0){
            activityStack.clear();
        }
    }
}
