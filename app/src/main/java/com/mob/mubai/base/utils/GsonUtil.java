package com.mob.mubai.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mob.mubai.base.helps.ResultJsonDeser;
import com.mob.mubai.data.DataResult;

/**
 * Created by lzw on 2016/12/17.
 * Gson
 */

public class GsonUtil {


        private static Gson gson;

        public static Gson getGson() {
                if (null == gson) {
                        synchronized (GsonUtil.class) {
                                if (null == gson) {
                                        gson = new GsonBuilder()
                                                  .registerTypeHierarchyAdapter(DataResult.class, new ResultJsonDeser())
                                                  .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                                                  .serializeNulls()
                                                  .create();
                                }
                        }
                }
                return gson;
        }
}
