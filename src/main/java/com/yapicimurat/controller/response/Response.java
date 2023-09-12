package com.yapicimurat.controller.response;

import java.io.Serializable;

public abstract class Response implements Serializable {
    protected boolean isSuccess;
    protected String message;

    protected Response(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
