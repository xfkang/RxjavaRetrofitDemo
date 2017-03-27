package com.itbird.retrofit.http;


import com.google.gson.Gson;
import com.itbird.utils.HttpConstants;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * Created by itbird on 2017/3/27
 */

public class CustomRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private final Gson gson;
    private final Type type;

    /**
     * 构造器
     */
    public CustomRequestBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
//        //加密
//        Log.i("xiaozhang", "request中传递的json数据：" + value.toString());
//        data.setData(XXTEA.Encrypt(value.toString(), HttpConstant.KEY));
//        String postBody = gson.toJson(data); //对象转化成json
//        Log.i("xiaozhang", "转化后的数据：" + postBody);
        return RequestBody.create(HttpConstants.MEDIA_TYPE, value.toString());
    }
}
