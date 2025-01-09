package com.example.springboot.exception;

import com.example.springboot.util.ExceptionCodeEnum;

public class FileStorageException extends RuntimeException {
    public FileStorageException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.getMessage());
    }
}
