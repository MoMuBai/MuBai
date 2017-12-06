package com.mb.mubai.base.util;

import com.mb.mubai.data.model.NoBodyEntity;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author: lzw
 * @date: 2017/12/6 上午10:02
 * @desc:
 */

public class NoBodyConverterFactory extends Converter.Factory {

    public static final NoBodyConverterFactory create() {
        return new NoBodyConverterFactory();
    }

    public NoBodyConverterFactory() {
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (NoBodyEntity.class.equals(type)) {
            return new Converter<ResponseBody, NoBodyEntity>() {
                @Override
                public NoBodyEntity convert(ResponseBody value) throws IOException {
                    return null;
                }
            };
        }
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
