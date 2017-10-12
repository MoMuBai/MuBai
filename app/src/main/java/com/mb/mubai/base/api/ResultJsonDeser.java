package com.mb.mubai.base.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mb.mubai.data.DataResult;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lzw on 2016/12/16.
 */

public class ResultJsonDeser implements JsonDeserializer<DataResult<?>> {
    @Override
    public DataResult<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DataResult dataResult = new DataResult();
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            String code = jsonObject.get("code").getAsString();
            dataResult.setCode(code);
            dataResult.setMsg(jsonObject.get("msg").getAsString());
            if (code.endsWith("200")) {
                return dataResult;
            }
            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            dataResult.setData(context.deserialize(jsonObject.get("data"), itemType));
            return dataResult;
        }
        return dataResult;
    }
}
