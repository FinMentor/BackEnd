package com.example.springboot.controller;

import com.example.springboot.dto.LoginResponseDTO;
import com.example.springboot.service.KaKaoOauthServiceImpl;
import com.example.springboot.service.NaverOauthServiceImpl;
import com.example.springboot.util.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class SocialLoginController {
    private final KaKaoOauthServiceImpl kaKaoOauthService;
    private final NaverOauthServiceImpl naverOauthService;

    @GetMapping("/login/code/kakao")
    public ResponseResult<LoginResponseDTO> oauthKakao(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
        LoginResponseDTO loginResponse = kaKaoOauthService.processKakaoLogin(code, response, request);
        return ResponseResult.success(loginResponse);
    }

    @GetMapping("/login/code/naver")
    public ResponseResult<LoginResponseDTO> oauthNaver(@RequestParam("code") String code,
                                                       @RequestParam("state") String state,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response) {
        LoginResponseDTO loginResponse = naverOauthService.processNaverLogin(code, state, request, response);
        return ResponseResult.success(loginResponse);
    }
}
