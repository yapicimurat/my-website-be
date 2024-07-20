package com.yapicimurat.web.controller.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    protected boolean isSuccess;
    protected String message;

    protected Response(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
