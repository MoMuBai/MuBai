package com.mb.mubai.ui.test.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.mb.mubai.ui.test.contract.SeekBarContract;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/4
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class SeekBarModel implements SeekBarContract.Model {
        @Override
        public String getShow() {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("dataType", 20);
                jsonObject.put("dataValue", "Base64编码的字符");
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("image", jsonObject);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(jsonObject1);
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("inputs", jsonArray);
                String str = new Gson().toJson(jsonObject2);
                return str;
        }
}
