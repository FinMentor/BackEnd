package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCodeEnum {
    DUPLICATED_ID(HttpStatus.CONFLICT, "동일한 아이디가 존재합니다."),
    MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    UNVERIFIED_PHONE(HttpStatus.FORBIDDEN, "휴대폰인증이 확인되지 않았습니다."),
    UNVERIFIED_EMAIL(HttpStatus.FORBIDDEN, "이메일인증이 확인되지 않았습니다."),
    NONEXISTENT_TERMS_OF_USE(HttpStatus.NOT_FOUND, "이용약관이 존재하지 않습니다."),
    NONEXISTENT_REQUIRED_TERMS_OF_USE(HttpStatus.BAD_REQUEST, "필수 이용약관이 존재하지 않습니다."),
    NONEXISTENT_VERIFIED_PHONE(HttpStatus.BAD_REQUEST, "휴대폰인증이 존재하지 않습니다."),
    NONEXISTENT_MEMBER(HttpStatus.NOT_FOUND, "멤버가 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;
}
