package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDTO;

public class ErrorDataResponse<T> extends DataResponse<T>{
    public ErrorDataResponse(T data, String message) {
        super(data, false, message);
    }
}
