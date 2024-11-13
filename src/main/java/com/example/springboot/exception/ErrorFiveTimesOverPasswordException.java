package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class ErrorFiveTimesOverPasswordException extends RuntimeException {
    public ErrorFiveTimesOverPasswordException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
