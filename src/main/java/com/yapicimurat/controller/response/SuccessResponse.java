package com.yapicimurat.controller.response;

public class SuccessResponse extends Response{
    public SuccessResponse(String message) {
        super(true, message);
    }
}
