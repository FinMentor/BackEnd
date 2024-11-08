package com.example.springboot.exception;

import java.util.NoSuchElementException;

public class FailGetMemberException extends NoSuchElementException {
    public FailGetMemberException(String message) {
        super(message);
    }
}
