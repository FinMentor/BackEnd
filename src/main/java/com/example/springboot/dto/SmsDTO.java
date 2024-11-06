package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SmsDTO {
    String phoneFirst;
    String phoneMiddle;
    String phoneLast;
    String phoneVerificationCode;
}
