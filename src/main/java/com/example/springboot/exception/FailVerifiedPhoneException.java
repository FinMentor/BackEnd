package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailVerifiedPhoneException extends RuntimeException {
    public FailVerifiedPhoneException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
