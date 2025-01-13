package com.example.springboot.controller;

import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.TermsAgreementDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemberControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void signup() throws Exception {
        String uri = "/api/v1/auth/signup";

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

        String content = objectMapper.writeValueAsString(memberSignupRequestDTO);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
