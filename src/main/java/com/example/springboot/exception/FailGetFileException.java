package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

import java.util.NoSuchElementException;

public class FailGetFileException extends NoSuchElementException {
    public FailGetFileException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
