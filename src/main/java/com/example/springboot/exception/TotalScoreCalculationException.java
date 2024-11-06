package com.example.springboot.exception;

public class TotalScoreCalculationException extends RuntimeException {
    public TotalScoreCalculationException(String message, Exception e) {
        super(message);
    }
}
