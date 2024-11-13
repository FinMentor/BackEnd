package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class ErrorRequiredValueValidationException extends IllegalArgumentException {
    public ErrorRequiredValueValidationException(StringBuilder sb, ExceptionCodeEnum exceptionCodeEnum) {
        super(sb.append(exceptionCodeEnum.getMessage()).toString());
    }
}
