package com.example.springboot.service;

import com.example.springboot.dto.AuthTokensDTO;

public interface AuthTokensGenerator {
    AuthTokensDTO generate(String id, String memberType, Long memberId);

    boolean validateTokens(String accessToken);

    String getMemberId(String token);
}
