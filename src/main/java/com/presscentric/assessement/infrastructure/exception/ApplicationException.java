package com.presscentric.assessement.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;

    public ApplicationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String message, HttpStatus httpStatus, Throwable throwable) {
        super(message, throwable);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
