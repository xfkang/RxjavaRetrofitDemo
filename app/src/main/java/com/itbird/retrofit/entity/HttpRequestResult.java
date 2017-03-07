package com.itbird.retrofit.entity;

/**
 * 请求结果封装类
 * Created by xfkang on 17/3/5.
 */
public class HttpRequestResult<T> {

    private int resultCode;
    private String resultMessage;
    //用来模仿Data
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
