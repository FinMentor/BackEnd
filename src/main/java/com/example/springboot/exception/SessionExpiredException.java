package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class SessionExpiredException extends RuntimeException {
    public SessionExpiredException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
