package com.example.springboot.security.service;

import com.example.springboot.entity.MemberEntity;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByMemberId(username);

        if (memberEntity != null) {
            // 비밀번호가 null인 경우 빈 문자열로 대체
            String password = memberEntity.getPassword();

            if (memberEntity.getSnsType() != null) {
                // 소셜 로그인 사용자
                password = password != null ? password : "";
            } else {
                // 일반 사용자
                if (password == null) {
                    throw new IllegalArgumentException("일반 사용자의 비밀번호는 null일 수 없습니다.");
                }
            }
            return org.springframework.security.core.userdetails.User.withUsername(memberEntity.getMemberId())
                    .password(password)
                    .roles("MEMBER")
                    .build();
        }
        throw new UsernameNotFoundException(ExceptionCodeEnum.SESSION_EXPIRATION.getMessage());
    }
}

