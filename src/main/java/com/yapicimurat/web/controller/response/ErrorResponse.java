package com.yapicimurat.web.controller.response;

public class ErrorResponse extends Response{
    public ErrorResponse(String message) {
        super(false, message);
    }
}
