package com.yapicimurat.exception;

import com.yapicimurat.constant.ExceptionConstant;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends EntityException {
    public EntityNotFoundException() {
        super(HttpStatus.NOT_FOUND, ExceptionConstant.ENTITY_NOT_FOUND);
    }

}
