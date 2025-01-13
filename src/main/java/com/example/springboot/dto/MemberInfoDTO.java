package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDTO {
    private Long memberId;
    private String nickname;
    private String profileImageUrl;
    private String answerTime;
    private String resultCode;
    private String resultMessage;
}
