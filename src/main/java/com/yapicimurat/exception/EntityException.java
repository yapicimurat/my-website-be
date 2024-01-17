package com.yapicimurat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

public abstract class EntityException extends RuntimeException {
    private HttpStatus httpStatus;
    private LocalDateTime dateTime = LocalDateTime.now();
    private WebRequest webRequest;
    protected EntityException(HttpStatus httpStatus, String message) {
        super(message);
        setHttpStatus(httpStatus);
    }
    protected void takeAction() {
        System.out.println("ENTITY EXCEPTION DETECTED");
    }
    protected WebRequest getWebRequest() {
        return webRequest;
    }
    protected void setWebRequest(WebRequest webRequest) {
        this.webRequest = webRequest;
    }
    protected HttpStatus getHttpStatus() {
        return httpStatus;
    }
    protected void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    protected LocalDateTime getDateTime() {
        return dateTime;
    }
}
