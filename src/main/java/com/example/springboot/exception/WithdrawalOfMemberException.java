package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class WithdrawalOfMemberException extends RuntimeException {
    public WithdrawalOfMemberException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
