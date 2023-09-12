package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDto;

public class SuccessDataResponse<T extends BaseDto> extends DataResponse<T>{
    public SuccessDataResponse(T data, String message) {
        super(data, true, message);
    }
}
