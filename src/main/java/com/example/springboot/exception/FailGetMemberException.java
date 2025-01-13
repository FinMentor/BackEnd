package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

import java.util.NoSuchElementException;

public class FailGetMemberException extends NoSuchElementException {
    public FailGetMemberException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
