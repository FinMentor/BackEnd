package com.example.springboot.exception;

public class TermsNotAgreedException extends RuntimeException {
    public TermsNotAgreedException(String message) {
        super(message);
    }
}
