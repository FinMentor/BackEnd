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
    NONEXISTENT_MEMBER(HttpStatus.NOT_FOUND, "멤버가 존재하지 않습니다."),
    NONEXISTENT_REQUIRED_VALUE(HttpStatus.BAD_REQUEST, "필수값입니다."),
    MISMATCH_ID_OR_PASSWORD(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."),
    WITHDRAWAL_MEMBER(HttpStatus.FORBIDDEN, "회원탈퇴한 멤버입니다."),
    ERROR_FIVE_TIMES_OVER_PASSWORD(HttpStatus.FORBIDDEN, "비밀번호 5회이상 틀린 멤버입니다."),
    SESSION_EXPIRED(HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다."),
    NONEXISTENT_POST(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;
}
