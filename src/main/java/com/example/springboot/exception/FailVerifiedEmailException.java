package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailVerifiedEmailException extends RuntimeException {
    public FailVerifiedEmailException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
