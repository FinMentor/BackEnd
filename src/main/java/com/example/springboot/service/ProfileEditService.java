package com.example.springboot.service;

import com.example.springboot.dto.ProfileEditDTO;

public interface ProfileEditService {
    // 특정 사용자의 프로필 정보를 가져옴
    ProfileEditDTO getProfile(String memberId);

    // 특정 사용자의 프로필 정보를 업데이트함
    void updateProfile(String memberId, ProfileEditDTO profileEditDTO);
}
