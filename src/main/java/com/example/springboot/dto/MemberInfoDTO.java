package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDTO {
    private Long memberId;
    private String nickname;
    private String profileImageUrl;
    private String answerTime;
}
