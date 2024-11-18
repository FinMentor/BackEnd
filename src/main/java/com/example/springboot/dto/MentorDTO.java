package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorDTO {
    private Long memberId;
    private String name;
    private String nickname;
    private String profileImageUrl;
    private String answerTime;
    private String mainCategoryName;
}
