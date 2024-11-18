package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

import java.util.NoSuchElementException;

public class FailGetNotificationException extends NoSuchElementException {
    public FailGetNotificationException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
