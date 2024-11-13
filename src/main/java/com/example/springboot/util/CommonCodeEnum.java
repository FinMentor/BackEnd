package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCodeEnum {
    YES("Y", null),
    NO("N", null),
    MASTER("master", null),
    ROOKIE("rookie", null),
    FIVE("", 5L);

    private String value;
    private Long num;
}
