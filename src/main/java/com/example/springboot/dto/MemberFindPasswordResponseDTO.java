package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberFindPasswordResponseDTO {
    String resultCode;
    String resultMessage;
}
