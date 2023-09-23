package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDTO;

public class SuccessDataResponse<T extends BaseDTO> extends DataResponse<T>{
    public SuccessDataResponse(T data, String message) {
        super(data, true, message);
    }
}
