package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDTO;

public class ErrorDataResponse<T extends BaseDTO> extends DataResponse<T>{
    public ErrorDataResponse(T data, boolean isSuccess, String message) {
        super(data, isSuccess, message);
    }
}
