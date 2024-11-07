package com.example.springboot.exception;

public class FailVerifiedPhoneException extends RuntimeException {
    public FailVerifiedPhoneException(String message) {
        super(message);
    }
}
