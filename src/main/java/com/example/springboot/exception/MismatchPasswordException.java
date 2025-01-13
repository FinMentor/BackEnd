package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class MismatchPasswordException extends RuntimeException {
    public MismatchPasswordException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
