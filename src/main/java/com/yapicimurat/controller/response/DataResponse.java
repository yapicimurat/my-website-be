package com.yapicimurat.controller.response;

public abstract class DataResponse<T> extends Response{

    protected T data;
    protected DataResponse(T data, boolean isSuccess, String message) {
        super(isSuccess, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
