package com.yapicimurat.exception;

import com.yapicimurat.constant.ExceptionConstant;
import com.yapicimurat.web.controller.response.ErrorDataResponse;
import com.yapicimurat.web.controller.response.ErrorResponse;
import com.yapicimurat.web.controller.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {EntityException.class})
    public ResponseEntity<Response> handleEntityException(EntityException exception, WebRequest webRequest) {
        exception.setWebRequest(webRequest);
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()),exception.getHttpStatus());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
        return new ErrorResponse(ExceptionConstant.INVALID_ARGUMENT);
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                               WebRequest webRequest) {
        final List<ObjectError> errorList =  exception.getBindingResult().getAllErrors();
        final Map<String, String> invalidFieldList = new HashMap<>();

        errorList.forEach(error -> invalidFieldList.put(((FieldError)error).getField(), error.getDefaultMessage()));

        return new ErrorDataResponse<>(invalidFieldList,"");
    }
}
