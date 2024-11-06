package com.example.springboot.controller;

import com.example.springboot.dto.*;
import com.example.springboot.service.MemberService;
import com.example.springboot.util.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private MemberService memberService;

    /**
     * 회원가입
     * <p>
     * 회원가입 정보를 입력하는 메소드이다.
     *
     * @param memberDTO
     * @return
     */
    @PutMapping("/signup")
    public ResponseResult<Integer> signup(@RequestBody MemberDTO memberDTO) {
        if (log.isInfoEnabled()) {
            log.info("signup memberDTO : {}", memberDTO);
        }
        return ResponseResult.success(memberService.signup(memberDTO));
    }

    @PostMapping("/login")
    public ResponseResult<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request, HttpServletResponse response) {
        LoginResponseDTO loginResponseDTO = memberService.login(loginRequest, request, response);
        return ResponseResult.success(loginResponseDTO);
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        memberService.logout(request, response);
        return ResponseResult.success();
    }

    @PostMapping("/find-id")
    public ResponseResult<List<Map<String, Object>>> findId(@RequestBody FindIdRequestDTO findIdRequestDTO) {
        //이름+휴대폰번호로 회원 찾기
        //일치하는 회원 데이터 리턴
        return ResponseResult.success(memberService.findIdByNameAndPhone(findIdRequestDTO));
    }

    @PostMapping("/find-password")
    public ResponseResult<Void> findPassword(@RequestBody FindPasswordRequestDTO findPasswordRequestDTO) {
        memberService.findPassword(findPasswordRequestDTO);
        return ResponseResult.success();
    }

    @PostMapping("/change-password")
    public ResponseResult<Integer> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        return ResponseResult.success(memberService.changePassword(changePasswordRequestDTO));
    }

    @GetMapping("/login/renew")
    public ResponseResult<?> renewLogin(@CookieValue(value = "refreshToken") String refreshToken, HttpServletRequest request, HttpServletResponse response) {
        if (log.isInfoEnabled()) {
            log.info("renewLogin refreshToken : {}, request : {}, response : {}", refreshToken, request, response);
        }
        return ResponseResult.success(memberService.renewLogin(refreshToken, request, response));
    }
}
