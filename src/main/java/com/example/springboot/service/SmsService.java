package com.example.springboot.service;


import com.example.springboot.dto.FindIdRequestDTO;
import com.example.springboot.dto.FindPasswordRequestDTO;
import com.example.springboot.dto.MemberDTO;
import com.example.springboot.dto.SmsDTO;

public interface SmsService {
    SmsDTO sendSms(SmsDTO smsDTO);

    int checkSmsVerification(SmsDTO smsDTO);

    boolean checkVerifyCodeForFindId(FindIdRequestDTO findIdRequestDTO);

    boolean checkEmailVerificationCode(String emailVerificationCode);

    boolean checkVerifyCodeForFindPassword(FindPasswordRequestDTO findPasswordRequestDTO);

    boolean checkVerifyCodeForFindSignup(MemberDTO memberDTO);
}
