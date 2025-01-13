package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;
import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
