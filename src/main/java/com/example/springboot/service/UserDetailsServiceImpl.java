package com.example.springboot.service;

import com.example.springboot.dao.MemberDAO;
import com.example.springboot.exception.SessionExpiredException;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberDAO memberDAO;

    /**
     * 멤버 조회
     * <p>
     * 멤버정보를 조회하는 메소드이다.
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberDAO.findById(username).map(
                        memberEntity -> User.withUsername(memberEntity.getId())
                                .password(memberEntity.getPassword())
                                .roles().build())
                .orElseThrow(() -> new SessionExpiredException(ExceptionCodeEnum.SESSION_EXPIRED));
    }
}
