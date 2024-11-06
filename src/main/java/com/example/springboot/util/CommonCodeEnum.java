package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonCodeEnum {
    NAVER("naver"),
    KAKAO("kakao"),
    START_TIME("000000"),
    END_TIME("235959"),
    SEND_NUMBER("010-4343-1517"),
    YES("Y"),
    NO("N");

    private String value;
}
