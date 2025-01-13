package com.example.springboot.service;

import com.example.springboot.dto.AuthTokensDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthTokensGeneratorImpl implements AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;    // 1시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 15;  // 15일

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 토큰 생성
     * <p>
     * 엑세스토큰과 리프레시토큰을 생성하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public AuthTokensDTO generate(String id, String memberType, Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtTokenProvider.accessTokenGenerate(id, memberType, memberId, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.refreshTokenGenerate(id, memberType, memberId, refreshTokenExpiredAt);

        return AuthTokensDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .grantType(BEARER_TYPE)
                .accessTokenExpireTime(ACCESS_TOKEN_EXPIRE_TIME)
                .refreshTokenExpireTime(REFRESH_TOKEN_EXPIRE_TIME).build();
    }

    /**
     * 토큰 검증
     * <p>
     * 엑세스토큰을 검증하는 메소드이다.
     *
     * @param accessToken
     * @return
     */
    @Override
    public boolean validateTokens(String accessToken) {
        return jwtTokenProvider.validateAccessToken(accessToken);
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
        return jwtTokenProvider.getMemberId(token);
    }
}
