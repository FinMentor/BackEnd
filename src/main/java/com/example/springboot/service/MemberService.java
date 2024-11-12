package com.example.springboot.service;

import com.example.springboot.dto.*;

import java.util.List;

public interface MemberService {
    MemberSignupResponseDTO signup(MemberSignupRequestDTO memberSignupRequestDTO);

    List<MemberFindIdDTO> findId(String name, String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode);

    MemberFindPasswordResponseDTO findPassword(MemberFindPasswordRequestDTO memberFindPasswordRequestDTO);
}
