package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class ErrorIdAndPasswordException extends RuntimeException {
    public ErrorIdAndPasswordException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
