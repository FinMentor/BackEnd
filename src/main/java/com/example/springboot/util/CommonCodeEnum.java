package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCodeEnum {
    YES("Y"),
    NO("N"),
    MASTER("master"),
    ROOKIE("rookie");

    private String value;
}
