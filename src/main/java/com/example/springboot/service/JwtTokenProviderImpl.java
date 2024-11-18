package com.example.springboot.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {
    private final Key key;

    public JwtTokenProviderImpl(@Value("${custom.jwt.secretKey}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 엑세스토큰 생성
     * <p>
     * 멤버아이디와 유효시간으로 엑세스토큰을 생성하는 메소드이다.
     *
     * @param subject
     * @param expiredAt
     * @return
     */
    @Override
    public String accessTokenGenerate(String subject, String memberType, Date expiredAt) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("memberType", memberType)
                .setIssuedAt(new Date())
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

    /**
     * 리프레시토큰 생성
     * <p>
     * 멤버아이디와 유효시간으로 리프레시토큰을 생성하는 메소드이다.
     *
     * @param subject
     * @param expiredAt
     * @return
     */
    @Override
    public String refreshTokenGenerate(String subject, String memberType, Date expiredAt) {
        return Jwts.builder()
                .setSubject(subject)
                .claim("memberType", memberType)
                .setIssuedAt(new Date())
                .setExpiration(expiredAt)
                .signWith(key)
                .compact();
    }

    /**
     * 엑세스토큰 검증
     * <p>
     * 엑세스토큰 유효성을 검증하는 메소드이다.
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean validateAccessToken(String accessToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 멤버아이디 조회
     * <p>
     * 토큰으로 멤버아이디를 조회하는 메소드이다.
     *
     * @param token
     * @return
     */
    @Override
    public String getMemberId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
