package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class ErrorRequiredValueValidation extends IllegalArgumentException {
    public ErrorRequiredValueValidation(StringBuilder sb, ExceptionCodeEnum exceptionCodeEnum) {
        super(sb.append(exceptionCodeEnum.getMessage()).toString());
    }
}
