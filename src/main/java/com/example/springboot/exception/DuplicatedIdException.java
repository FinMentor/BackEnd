package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class DuplicatedIdException extends RuntimeException {
    public DuplicatedIdException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
