package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterMemberDTO {
    private Long memberId;
    private Long mainCategoryId;
    private String answerTime;
    private Double followingCount;
}
