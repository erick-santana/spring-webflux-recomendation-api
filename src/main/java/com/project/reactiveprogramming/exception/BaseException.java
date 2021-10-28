package com.project.reactiveprogramming.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final int httpStatus;
    private final String errorCode;

    protected BaseException(int httpStatus, String errorCode, String description) {
        super(description);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}