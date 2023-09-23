package com.yapicimurat.controller.response;

import com.yapicimurat.dto.BaseDTO;

public abstract class DataResponse<T extends BaseDTO> extends Response{

    protected T data;
    protected DataResponse(T data, boolean isSuccess, String message) {
        super(isSuccess, message);
        this.data = data;
    }


}
