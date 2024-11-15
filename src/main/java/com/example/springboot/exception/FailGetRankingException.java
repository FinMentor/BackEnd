package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FailGetRankingException extends RuntimeException {
    public FailGetRankingException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
