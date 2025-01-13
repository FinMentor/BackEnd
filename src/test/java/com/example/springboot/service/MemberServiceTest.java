package com.example.springboot.service;

import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;
import com.example.springboot.dto.TermsAgreementDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void signup() {
        List<TermsAgreementDTO> termsAgreementDTOList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            TermsAgreementDTO termsAgreementDTO = TermsAgreementDTO.builder()
                    .termsOfUseId((long) i)
                    .required('Y').build();
            termsAgreementDTOList.add(termsAgreementDTO);
        }

        MemberSignupRequestDTO memberSignupRequestDTO = MemberSignupRequestDTO.builder()
                .id("testUser1")
                .password("123456")
                .passwordConfirmation("123456")
                .phoneFirst("010")
                .phoneMiddle("1111")
                .phoneLast("1111")
                .phoneVerificationCode("123456")
                .phoneVerifiedStatus("Y")
                .name("김길동")
                .email("testUser1@gmail.com")
                .emailVerificationCode("123456")
                .emailVerifiedStatus("Y")
                .introduce("안녕하세요.")
                .termsAgreementDTOList(termsAgreementDTOList).build();

        MemberSignupResponseDTO memberSignupResponseDTO = memberService.signup(memberSignupRequestDTO);
        assertNotNull(memberSignupResponseDTO);
    }
}
