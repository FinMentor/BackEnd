package com.example.springboot.service;

import com.example.springboot.dto.LoginResponseDTO;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface KakaoOauthService {
    String getAccessToken(String authorize_code);

    JsonObject getMemberInfo(String access_Token);

    LoginResponseDTO processKakaoLogin(String code, HttpServletResponse response, HttpServletRequest request);

    String kakaoLogout();
}
