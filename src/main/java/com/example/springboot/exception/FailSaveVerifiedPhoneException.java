package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailSaveVerifiedPhoneException extends IllegalArgumentException {
    public FailSaveVerifiedPhoneException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
