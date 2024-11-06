package com.example.springboot.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDTO {
    private String memberId;
    private String password;
    private String passwordConfirmation;
    private String phoneFirst;
    private String phoneMiddle;
    private String phoneLast;
    private String phoneVerificationCode;
    private String name;
    private String email;
    private String emailVerificationCode;
    private List<TermsAgreementDTO> termsAgreementDTOList;
}
