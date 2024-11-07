package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberSignupResponseDTO {
    String resultCode;
    String resultMessage;
}
