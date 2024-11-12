package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MasterRecommendDTO {
    private String memberId;
    private String name;
    private String nickname;
    private String profileImageUrl;
    private String resultCode;
    private String resultMessage;
}
