package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberAnswerDTO {
    //    private String memberId;
    private Long questionId;
    private Long optionId;
}
