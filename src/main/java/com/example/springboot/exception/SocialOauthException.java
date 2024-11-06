package com.example.springboot.exception;

public class SocialOauthException extends RuntimeException {
    public SocialOauthException(String message) {
        super(message);
    }
}
