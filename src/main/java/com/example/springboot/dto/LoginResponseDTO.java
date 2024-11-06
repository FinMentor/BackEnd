package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
}
