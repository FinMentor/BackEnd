package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorOfThreeDTO {
    private Long memberId;
    private String name;
    private String nickname;
    private String profileImageUrl;
    private Long mainCategoryId;
    private String mainCategoryName;
    private Double averageStar;
}
