package com.example.springboot.controller;

import com.example.springboot.dto.ProfileEditDTO;
import com.example.springboot.service.ProfileEditService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members/profile_edit")
@RequiredArgsConstructor
@Slf4j
public class ProfileEditController {
    private ProfileEditService profileEditService;

    // 사용자 ID로 프로필 정보를 조회
    @GetMapping
    public ResponseResult<ProfileEditDTO> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("프로필 조회 요청 - 사용자 ID: {}", userDetails.getUsername());
        ProfileEditDTO profile = profileEditService.getProfile(userDetails.getUsername());
        if (profile != null) {
            log.info("프로필 조회 완료 - 사용자 ID: {}", userDetails.getUsername());
            return new ResponseResult<>(profile, HttpStatus.OK);
        } else {
            log.warn("프로필 조회 실패 - 사용자 ID: {}", userDetails.getUsername());
            return new ResponseResult<>(HttpStatus.NOT_FOUND);
        }
    }

    // 사용자 ID로 프로필 정보를 업데이트
    @PutMapping
    public ResponseResult<Void> updateProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ProfileEditDTO profileEditDTO) {
        log.info("프로필 업데이트 요청 - 사용자 ID: {}, 수정할 정보: {}", userDetails.getUsername(), profileEditDTO);
        try {
            profileEditService.updateProfile(userDetails.getUsername(), profileEditDTO);
            log.info("프로필 업데이트 완료 - 사용자 ID: {}", userDetails.getUsername());
            return new ResponseResult<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("프로필 업데이트 실패 - 사용자 ID: {}, 오류: {}", userDetails.getUsername(), e.getMessage());
            return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
