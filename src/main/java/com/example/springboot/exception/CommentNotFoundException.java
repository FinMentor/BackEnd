package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
