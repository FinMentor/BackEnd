package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailGetMainCategoryException extends RuntimeException {
    public FailGetMainCategoryException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
