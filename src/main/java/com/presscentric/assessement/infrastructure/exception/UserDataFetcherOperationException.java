package com.presscentric.assessement.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class UserDataFetcherOperationException extends ApplicationException {
    public UserDataFetcherOperationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public UserDataFetcherOperationException(String message, Throwable throwable) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, throwable);
    }
}
