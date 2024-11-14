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
        log.error("ExceptionAdvice duplicatedIdException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.DUPLICATED_ID.getHttpStatus(), ResultCodeEnum.DUPLICATED_ID.getValue(), ResultCodeEnum.DUPLICATED_ID.getMessage());
    }

    // 비밀번호, 비밀번호 확인 불일치
    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseResult<ErrorResponse> mismatchPasswordException(MismatchPasswordException e) {
        log.error("ExceptionAdvice mismatchPasswordException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.MISMATCH_PASSWORD.getHttpStatus(), ResultCodeEnum.MISMATCH_PASSWORD.getValue(), ResultCodeEnum.MISMATCH_PASSWORD.getMessage());
    }

    // 휴대폰인증 실패
    @ExceptionHandler(FailVerifiedPhoneException.class)
    public ResponseResult<ErrorResponse> failVerifiedPhoneException(FailVerifiedPhoneException e) {
        log.error("ExceptionAdvice failVerifiedPhoneException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.UNVERIFIED_PHONE.getHttpStatus(), ResultCodeEnum.UNVERIFIED_PHONE.getValue(), ResultCodeEnum.UNVERIFIED_PHONE.getMessage());
    }

    // 이메일인증 실패
    @ExceptionHandler(FailVerifiedEmailException.class)
    public ResponseResult<ErrorResponse> failVerifiedEmailException(FailVerifiedEmailException e) {
        log.error("ExceptionAdvice failVerifiedEmailException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.UNVERIFIED_EMAIL.getHttpStatus(), ResultCodeEnum.UNVERIFIED_EMAIL.getValue(), ResultCodeEnum.UNVERIFIED_EMAIL.getMessage());
    }

    // 이용약관조회 실패
    @ExceptionHandler(FailGetTermsOfUseException.class)
    public ResponseResult<ErrorResponse> failGetTermsOfUseException(FailGetTermsOfUseException e) {
        log.error("ExceptionAdvice failGetTermsOfUseException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_TERMS_OF_USE.getValue(), ResultCodeEnum.NONEXISTENT_TERMS_OF_USE.getMessage());
    }

    // 필수이용약관 누락
    @ExceptionHandler(FailSaveRequiredTermsOfUseException.class)
    public ResponseResult<ErrorResponse> failSaveRequiredTermsOfUseException(FailSaveRequiredTermsOfUseException e) {
        log.error("ExceptionAdvice failSaveRequiredTermsOfUseException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getValue(), ResultCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getMessage());
    }

    // 휴대폰인증 누락
    @ExceptionHandler(FailSaveVerifiedPhoneException.class)
    public ResponseResult<ErrorResponse> failSaveVerifiedPhoneException(FailSaveVerifiedPhoneException e) {
        log.error("ExceptionAdvice failSaveVerifiedPhoneException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_VERIFIED_PHONE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_VERIFIED_PHONE.getValue(), ResultCodeEnum.NONEXISTENT_VERIFIED_PHONE.getMessage());
    }

    // 존재하지않는 멤버
    @ExceptionHandler(FailGetMemberException.class)
    public ResponseResult<ErrorResponse> failGetMemberException(FailGetMemberException e) {
        log.error("ExceptionAdvice failGetMemberException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_MEMBER.getHttpStatus(), ResultCodeEnum.NONEXISTENT_MEMBER.getValue(), ResultCodeEnum.NONEXISTENT_MEMBER.getMessage());
    }

    // 필수값검증 오류
    @ExceptionHandler(ErrorRequiredValueValidationException.class)
    public ResponseResult<ErrorResponse> errorRequiredValueValidationException(ErrorRequiredValueValidationException e) {
        log.error("ExceptionAdvice errorRequiredValueValidationException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE.getHttpStatus(), ResultCodeEnum.NONEXISTENT_REQUIRED_VALUE.getValue(), ResultCodeEnum.NONEXISTENT_REQUIRED_VALUE.getMessage());
    }

    // 아이디 또는 비밀번호 불일치
    @ExceptionHandler(ErrorIdAndPasswordException.class)
    public ResponseResult<ErrorResponse> errorIdAndPasswordException(ErrorIdAndPasswordException e) {
        log.error("ExceptionAdvice errorIdAndPasswordException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.MISMATCH_ID_OR_PASSWORD.getHttpStatus(), ResultCodeEnum.MISMATCH_ID_OR_PASSWORD.getValue(), ResultCodeEnum.MISMATCH_ID_OR_PASSWORD.getMessage());
    }

    // 회원탈퇴한 멤버
    @ExceptionHandler(WithdrawalOfMemberException.class)
    public ResponseResult<ErrorResponse> withdrawalOfMemberException(WithdrawalOfMemberException e) {
        log.error("ExceptionAdvice withdrawalOfMemberException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.WITHDRAWAL_MEMBER.getHttpStatus(), ResultCodeEnum.WITHDRAWAL_MEMBER.getValue(), ResultCodeEnum.WITHDRAWAL_MEMBER.getMessage());
    }

    // 비밀번호 틀린 횟수 5회이상
    @ExceptionHandler(ErrorFiveTimesOverPasswordException.class)
    public ResponseResult<ErrorResponse> errorFiveTimesOverPasswordException(ErrorFiveTimesOverPasswordException e) {
        log.error("ExceptionAdvice errorFiveTimesOverPasswordException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.ERROR_FIVE_TIMES_OVER_PASSWORD.getHttpStatus(), ResultCodeEnum.ERROR_FIVE_TIMES_OVER_PASSWORD.getValue(), ResultCodeEnum.ERROR_FIVE_TIMES_OVER_PASSWORD.getMessage());
    }

    // 세션 만료
    @ExceptionHandler(SessionExpiredException.class)
    public ResponseResult<ErrorResponse> sessionExpiredException(SessionExpiredException e) {
        log.error("ExceptionAdvice sessionExpiredException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.SESSION_EXPIRED.getHttpStatus(), ResultCodeEnum.SESSION_EXPIRED.getValue(), ResultCodeEnum.SESSION_EXPIRED.getMessage());
    }

    // 게시글 찾기 실패
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseResult<ErrorResponse> postNotFoundException(PostNotFoundException e) {
        log.error("ExceptionAdvice postNotFoundException : {}", e.getMessage());

        return ResponseResult.fail(ExceptionCodeEnum.NONEXISTENT_POST.getHttpStatus(),ResultCodeEnum.NONEXISTENT_POST.getValue(), ResultCodeEnum.NONEXISTENT_POST.getMessage() );
    }
}
