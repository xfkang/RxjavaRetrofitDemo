package com.itbird.retrofit.http;

import android.util.Log;
import com.google.gson.Gson;
import com.itbird.retrofit.entity.HttpRequestResult;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 自定义gson转换
 * Created by xfkang on 2017/3/17.
 */
class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("Network", "response>>" + response);
        //httpResult 只解析result字段
        HttpRequestResult httpResult = gson.fromJson(response, HttpRequestResult.class);
        //
        if (httpResult.getResultCode() != 0) {
            throw new ApiException(100);
        }
        return gson.fromJson(response, type);
    }
}
