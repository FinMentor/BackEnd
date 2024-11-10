package com.example.springboot.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberFindidResponseDTO {
    private String memberId;
    private String snsType;
    private LocalDateTime createdAt;
}
