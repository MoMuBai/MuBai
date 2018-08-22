package com.lzw.library.utils;

import com.google.gson.Gson;

/**
 * Author: lzw
 * Date: 2018/5/15
 * Description: This is GsonUtil
 */

public class GsonUtil {
    public static void gson() {
        Gson gson = new Gson();
        String json = gson.toJson("{'data':'123'}");
        To.d(json);
    }
}
