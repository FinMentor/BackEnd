package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String resultCode;
    private String resultMessage;
}
