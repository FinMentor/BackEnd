package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberProfileDTO {
    private String memberId;          // 사용자 ID
    private String nickname;         // 사용자 닉네임
    private String investmentTypeName; // 투자 성향
    private double totalAssets;      // 자산 총액
    private String profileImageUrl;  // 프로필 사진 URL
    private String name;            // 사용자 이름
}
