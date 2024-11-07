package com.example.springboot.service;

import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;

public interface MemberService {
    MemberSignupResponseDTO signup(MemberSignupRequestDTO memberSignupRequestDTO);
}
