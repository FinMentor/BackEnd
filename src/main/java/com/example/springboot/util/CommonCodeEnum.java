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
    FIVE("", 5L),
    WEEKLY("weekly", null),
    MONTHLY("monthly", null),
    NORMAL("normal", null),
    EXPERT("expert", null),
    REDIS_KEY("RK:", null);

    private String value;
    private Long num;
}
