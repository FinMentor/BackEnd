package com.example.springboot.exception;

public class FailSaveRequiredTermsOfUseException extends RuntimeException {
    public FailSaveRequiredTermsOfUseException(String message) {
        super(message);
    }
}
