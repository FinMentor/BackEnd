package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailGetTermsOfUseException extends RuntimeException {
    public FailGetTermsOfUseException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
