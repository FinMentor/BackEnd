package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailSaveRequiredTermsOfUseException extends RuntimeException {
    public FailSaveRequiredTermsOfUseException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
