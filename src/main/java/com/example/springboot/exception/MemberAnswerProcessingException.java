package com.example.springboot.exception;

public class MemberAnswerProcessingException extends RuntimeException {
    public MemberAnswerProcessingException(String message) {
        super(message);
    }

}
