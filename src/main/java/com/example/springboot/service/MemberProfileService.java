package com.example.springboot.service;

import com.example.springboot.dto.MemberProfileDTO;

public interface MemberProfileService {
    MemberProfileDTO getMemberProfile(String memberId);
}
