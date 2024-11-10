package com.example.springboot.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS("0000", "성공"),
    FAIL("9999", "실패"),
    DUPLICATED_ID("0001", "회원가입시 중복된 아이디"),
    MISMATCH_PASSWORD("0002", "비밀번호, 비밀번호 확인 불일치"),
    UNVERIFIED_PHONE("0003", "휴대폰인증 실패"),
    UNVERIFIED_EMAIL("0004", "이메일인증 실패"),
    NONEXISTENT_TERMS_OF_USE("0005", "이용약관 조회 실패"),
    NONEXISTENT_REQUIRED_TERMS_OF_USE("0006", "필수이용약관 누락"),
    NONEXISTENT_VERIFIED_PHONE("0007", "휴대폰인증 누락"),
    NONEXISTENT_MEMBER("0008", "존재하지않는 멤버"),
    NONEXISTENT_REQUIRED_VALUE("0009", "필수값검증 오류");

    private String value;
    private String message;
}
