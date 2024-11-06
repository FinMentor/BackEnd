package com.example.springboot.service;

import com.example.springboot.dto.LoginResponseDTO;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface NaverOauthService {
    String getNaverAccessToken(String authorize_code, String state);

    JsonObject getMemberInfo(String access_Token);

    LoginResponseDTO processNaverLogin(String code, String state, HttpServletRequest request, HttpServletResponse response);
}
