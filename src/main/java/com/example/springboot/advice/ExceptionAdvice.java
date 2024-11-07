package com.example.springboot.advice;

import com.example.springboot.exception.*;
import com.example.springboot.util.ErrorResponse;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResponseResult;
import com.example.springboot.util.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    // 회원가입시 중복된 아이디
    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseResult<ErrorResponse> duplicatedIdException(DuplicatedIdException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice duplicatedIdException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.DUPLICATED_ID.getHttpStatus(), ResultCodeEnum.DUPLICATED_ID.getValue(), ResultCodeEnum.DUPLICATED_ID.getMessage());
    }

    // 비밀번호, 비밀번호 확인 불일치
    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseResult<ErrorResponse> mismatchPasswordException(MismatchPasswordException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice mismatchPasswordException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.MISMATCH_PASSWORD.getHttpStatus(), ResultCodeEnum.MISMATCH_PASSWORD.getValue(), ResultCodeEnum.MISMATCH_PASSWORD.getMessage());
    }

    // 휴대폰인증 실패
    @ExceptionHandler(FailVerifiedPhoneException.class)
    public ResponseResult<ErrorResponse> failVerifiedPhoneException(FailVerifiedPhoneException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice failVerifiedPhoneException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.UNVERIFIED_PHONE.getHttpStatus(), ResultCodeEnum.UNVERIFIED_PHONE.getValue(), ResultCodeEnum.UNVERIFIED_PHONE.getMessage());
    }

    // 이메일인증 실패
    @ExceptionHandler(FailVerifiedEmailException.class)
    public ResponseResult<ErrorResponse> failVerifiedEmailException(FailVerifiedEmailException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice failVerifiedEmailException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.UNVERIFIED_EMAIL.getHttpStatus(), ResultCodeEnum.UNVERIFIED_EMAIL.getValue(), ResultCodeEnum.UNVERIFIED_EMAIL.getMessage());
    }

    // 이용약관조회 실패
    @ExceptionHandler(FailGetTermsOfUseException.class)
    public ResponseResult<ErrorResponse> failGetTermsOfUseException(FailGetTermsOfUseException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice failGetTermsOfUseException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_TERMS_OF_USE.getValue(), ResultCodeEnum.NONEXISTENT_TERMS_OF_USE.getMessage());
    }

    // 필수이용약관 누락
    @ExceptionHandler(FailSaveRequiredTermsOfUseException.class)
    public ResponseResult<ErrorResponse> failSaveRequiredTermsOfUseException(FailSaveRequiredTermsOfUseException e) {
        if (log.isErrorEnabled()) {
            log.error("ExceptionAdvice failSaveRequiredTermsOfUseException : {}", e.getMessage());
        }
        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getValue(), ResultCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getMessage());
    }
}
