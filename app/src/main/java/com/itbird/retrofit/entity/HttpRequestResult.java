package com.itbird.retrofit.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 请求结果封装类
 * Created by itbird on 17/3/5.
 */
public class HttpRequestResult<T> {

    @SerializedName(value = "ResValue")
    private int resultCode;

    @SerializedName(value = "ResInfo")
    private String resultMessage;

    @SerializedName(value = "ResData")
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
