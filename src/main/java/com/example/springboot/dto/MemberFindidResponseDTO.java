package com.example.springboot.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberFindidResponseDTO {
    private String memberId;
    private String snsType;
    private Timestamp createdAt;
}
