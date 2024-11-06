package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS("0000", "성공"),

    FAIL("9999", "실패");

    private String value;
    private String message;
}
