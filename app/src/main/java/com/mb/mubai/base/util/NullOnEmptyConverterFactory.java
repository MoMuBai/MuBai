package com.mb.mubai.base.util;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author: lzw
 * @date: 2017/12/6 上午9:59
 * @desc:
 */

public class NullOnEmptyConverterFactory extends Converter.Factory {

    public static final NullOnEmptyConverterFactory create() {
        return new NullOnEmptyConverterFactory();
    }

    public NullOnEmptyConverterFactory() {
    }


    @MethodInfo(date = "2017-12-06 22:14:20", Desc = "需要重写这里的responseBodyConverter")
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(ResponseBody value) throws IOException {
                /**
                 * 当Body的Content-Length为0的时候就是没有Body的时候直接返回null
                 */
                if (value.contentLength() == 0) {
                    return null;
                }
                /**
                 * 这里如果有Body的时候正常返回
                 */
                return delegate.convert(value);
            }
        };
    }


    @MethodInfo(date = "2017-12-06 22:15:49", Desc = "这里的requestBodyConverter不需要重写")
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @MethodInfo(date = "2017-12-06 22:16:36", Desc = "这里的stringConverter不需要重写")
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}

