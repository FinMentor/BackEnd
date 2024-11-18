package com.example.springboot.service;

import java.util.Date;

public interface JwtTokenProvider {
    String accessTokenGenerate(String subject, String memberType, Date expiredAt);

    String refreshTokenGenerate(String subject, String memberType, Date expiredAt);

    boolean validateAccessToken(String accessToken);

    String getMemberId(String token);
}
