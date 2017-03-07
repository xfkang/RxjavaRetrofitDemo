package com.itbird.retrofit.http;

/**
 * http请求结果错误统一处理封装
 * Created by xfkang on 17/3/10.
 */
public class ApiException extends RuntimeException {

    public static final int NOT_404_FOUND = -1;

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code){
        String message = "";
        switch (code) {
            case NOT_404_FOUND:
                message = "404 NOT FOUND";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}

