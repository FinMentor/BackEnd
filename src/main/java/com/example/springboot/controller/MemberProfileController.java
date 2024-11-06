package com.example.springboot.controller;

import com.example.springboot.dto.MemberProfileDTO;
import com.example.springboot.service.MemberProfileService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member/profile")
@RequiredArgsConstructor
@Slf4j
public class MemberProfileController {
    private final MemberProfileService memberProfileService;

    @GetMapping
    public ResponseResult<MemberProfileDTO> getMemberProfile(@AuthenticationPrincipal UserDetails userDetails) {

        MemberProfileDTO memberProfile = memberProfileService.getMemberProfile(userDetails.getUsername());

        if (memberProfile != null) {
            return new ResponseResult<>(memberProfile, HttpStatus.OK);
        } else {
            return new ResponseResult<>(HttpStatus.NOT_FOUND);
        }
    }
}
