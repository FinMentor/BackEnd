package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS("0000", "성공"),

    FAIL("9999", "실패"),

    YES("Y", ""),

    NO("N", "");

    private String value;
    private String message;
}
