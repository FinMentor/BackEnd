package com.example.springboot.controller;

import com.example.springboot.dto.MemberFindidResponseDTO;
import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;
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
        if (log.isInfoEnabled()) {
            log.info("signup memberSignupRequestDTO : {}", memberSignupRequestDTO);
        }
        return ResponseResult.success(memberService.signup(memberSignupRequestDTO));
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
    public ResponseResult<List<MemberFindidResponseDTO>> findId(@RequestParam String name, @RequestParam String phoneFirst, @RequestParam String phoneMiddle, @RequestParam String phoneLast, @RequestParam String phoneVerificationCode) {
        if (log.isInfoEnabled()) {
            log.info("findId name : {}, phoneFirst : {}, phoneMiddle : {}, phoneLast : {}, phoneVerificationCode : {}", name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);
        }
        return ResponseResult.success(memberService.findid(name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode));
    }
}
