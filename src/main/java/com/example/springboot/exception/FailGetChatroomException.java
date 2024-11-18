package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

import java.util.NoSuchElementException;

public class FailGetChatroomException extends NoSuchElementException {
    public FailGetChatroomException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
