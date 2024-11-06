package com.example.springboot.controller;

import com.example.springboot.dto.SmsDTO;
import com.example.springboot.service.SmsService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
@Slf4j
public class SMSController {
    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseResult<SmsDTO> sendSms(@RequestBody SmsDTO smsDTO) {
        if (log.isInfoEnabled()) {
            log.info("sendSms smsDTO : {}", smsDTO.toString());
        }
        return ResponseResult.success(smsService.sendSms(smsDTO));
    }

    @PostMapping("/verification")
    public ResponseResult<Integer> checkSmsVerification(@RequestBody SmsDTO smsDTO) {
        if (log.isInfoEnabled()) {
            log.info("checkSmsVerification smsDTO : {}", smsDTO.toString());
        }
        return ResponseResult.success(smsService.checkSmsVerification(smsDTO));
    }
}
