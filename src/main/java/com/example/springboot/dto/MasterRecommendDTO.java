package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterRecommendDTO {
    private String memberId;
    private String name;
    private String nickname;
    private String profileImageUrl;
    private String resultCode;
    private String resultMessage;
}
