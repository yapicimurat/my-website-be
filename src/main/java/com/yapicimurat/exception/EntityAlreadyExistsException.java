package com.yapicimurat.exception;

import com.yapicimurat.constant.ExceptionConstant;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends EntityException {
    public EntityAlreadyExistsException() {
        super(HttpStatus.CONFLICT, ExceptionConstant.ENTITY_ALREADY_EXISTS);
    }
}
