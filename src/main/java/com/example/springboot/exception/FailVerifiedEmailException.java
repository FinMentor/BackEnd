package com.example.springboot.exception;

public class FailVerifiedEmailException extends RuntimeException {
    public FailVerifiedEmailException(String message) {
        super(message);
    }
}
