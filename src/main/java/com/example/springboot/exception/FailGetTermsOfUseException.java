package com.example.springboot.exception;

public class FailGetTermsOfUseException extends RuntimeException {
    public FailGetTermsOfUseException(String message) {
        super(message);
    }
}
