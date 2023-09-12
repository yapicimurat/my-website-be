package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDto;

public class ErrorDataResponse<T extends BaseDto> extends DataResponse<T>{
    public ErrorDataResponse(T data, boolean isSuccess, String message) {
        super(data, isSuccess, message);
    }
}
