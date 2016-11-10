package com.mob.mubai.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mubai on 2016/11/8.
 */
public final class TimeUtil {

    /**
     * 根据时间戳返回字符串
     *
     * */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
