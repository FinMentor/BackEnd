package com.example.springboot.service;

import java.util.Date;

public interface JwtTokenProvider {
    String accessTokenGenerate(String subject, Date expiredAt);

    String refreshTokenGenerate(String subject, Date expiredAt);

    boolean validateAccessToken(String accessToken);

    String getMemberId(String token);
}
