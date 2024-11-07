package com.example.springboot.controller;

import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;
import com.example.springboot.service.MemberService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PutMapping("/signup")
    public ResponseResult<MemberSignupResponseDTO> signup(@RequestBody MemberSignupRequestDTO memberSignupRequestDTO) {
        if (log.isInfoEnabled()) {
            log.info("signup memberSignupRequestDTO : {}", memberSignupRequestDTO);
        }
        return ResponseResult.success(memberService.signup(memberSignupRequestDTO));
    }
}
