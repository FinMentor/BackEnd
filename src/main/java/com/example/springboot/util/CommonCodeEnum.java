package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCodeEnum {
    YES("Y", null),
    NO("N", null),
    MENTOR("mentor", null),
    MENTEE("mentee", null),
    FIVE("", 5L);

    private String value;
    private Long num;
}
