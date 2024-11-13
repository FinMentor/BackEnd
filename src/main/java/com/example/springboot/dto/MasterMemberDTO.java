package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterMemberDTO {
    private Long memberId;
    private Long mainCategoryId;
    private String answerTime;
    private Double followingCount;
}
