package com.example.springboot.controller;

import com.example.springboot.dto.*;
import com.example.springboot.service.MemberService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     * <p>
     * 회원가입시 필요한 정보가 들어오는 메소드이다.
     *
     * @param memberSignupRequestDTO
     * @return
     */
    @PostMapping("/signup")
    public ResponseResult<MemberSignupResponseDTO> signup(@RequestBody MemberSignupRequestDTO memberSignupRequestDTO) {
        log.info("signup memberSignupRequestDTO : {}", memberSignupRequestDTO);

        return ResponseResult.success(memberService.signup(memberSignupRequestDTO));
    }

    /**
     * 로그인
     * <p>
     * 아이디와 비밀번호로 로그인하는 메소드이다.
     *
     * @param memberLoginRequestDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseResult<MemberLoginResponseDTO> login(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
        log.info("login memberLoginRequestDTO : {}", memberLoginRequestDTO);

        return ResponseResult.success(memberService.login(memberLoginRequestDTO));
    }

    /**
     * 아이디 찾기
     * <p>
     * 이름과 휴대폰번호로 아이디를 찾는 메소드이다.
     *
     * @param name
     * @param phoneFirst
     * @param phoneMiddle
     * @param phoneLast
     * @param phoneVerificationCode
     * @return
     */
    @GetMapping("/find-id")
    public ResponseResult<List<MemberFindIdDTO>> findId(@RequestParam String name, @RequestParam String phoneFirst, @RequestParam String phoneMiddle, @RequestParam String phoneLast, @RequestParam String phoneVerificationCode) {
        log.info("findId name : {}, phoneFirst : {}, phoneMiddle : {}, phoneLast : {}, phoneVerificationCode : {}", name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);

        return ResponseResult.success(memberService.findId(name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode));
    }

    /**
     * 비밀번호 찾기
     * <p>
     * 아이디와 이름, 휴대폰번호로 비밀번호를 변경하는 메소드이다.
     *
     * @param memberFindPasswordRequestDTO
     * @return
     */
    @PutMapping("/find-password")
    public ResponseResult<MemberFindPasswordResponseDTO> findPassword(@RequestBody MemberFindPasswordRequestDTO memberFindPasswordRequestDTO) {
        log.info("findPassword memberFindPasswordRequestDTO : {}", memberFindPasswordRequestDTO);

        return ResponseResult.success(memberService.findPassword(memberFindPasswordRequestDTO));
    }

    /**
     * 로그인 갱신
     * <p>
     * 로그인을 갱신하는 메소드이다.
     *
     * @param memberLoginRenewRequestDTO
     * @return
     */
    @PostMapping("/login-renew")
    public ResponseResult<MemberLoginRenewResponseDTO> loginRenew(@RequestBody MemberLoginRenewRequestDTO memberLoginRenewRequestDTO) {
        log.info("loginRenew memberLoginRenewRequestDTO : {}", memberLoginRenewRequestDTO);

        return ResponseResult.success(memberService.loginRenew(memberLoginRenewRequestDTO));
    }
}
