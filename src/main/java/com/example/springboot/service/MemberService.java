package com.example.springboot.service;

import com.example.springboot.dto.MemberFindidResponseDTO;
import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;

import java.util.List;

public interface MemberService {
    MemberSignupResponseDTO signup(MemberSignupRequestDTO memberSignupRequestDTO);

    List<MemberFindidResponseDTO> findId(String name, String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode);
}
