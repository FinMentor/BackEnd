package com.example.springboot.exception;

public class MemberIdNotFoundException extends RuntimeException {
    public MemberIdNotFoundException(String message) {
        super(message);
    }
}
