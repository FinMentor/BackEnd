package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageSendRequestDTO {
    private Long chatroomId;
    private String content;
    private String nickname;
    private Long memberId;
    private String profileImageUrl;
}
