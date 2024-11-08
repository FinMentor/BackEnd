package com.example.springboot.exception;

public class FailSaveVerifiedPhoneException extends IllegalArgumentException {
    public FailSaveVerifiedPhoneException(String message) {
        super(message);
    }
}
