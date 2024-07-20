package com.yapicimurat.web.controller.response;


public class SuccessDataResponse<T> extends DataResponse<T>{
    public SuccessDataResponse(T data, String message) {
        super(data, true, message);
    }

    public static <T> SuccessDataResponse<T> createSuccessDataResponse(T data, String message) {
        return new SuccessDataResponse<>(data, message);
    }
}
