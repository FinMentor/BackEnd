package com.example.springboot.exception;

public class NoMatchingMemberException extends RuntimeException {
    public NoMatchingMemberException(String message) {
        super(message);
    }
}
