package com.example.springboot.service;

import java.util.Date;

public interface JwtTokenProvider {
    String accessTokenGenerate(String subject, Date expiredAt);

    String refreshTokenGenerate(Date expiredAt);
}
