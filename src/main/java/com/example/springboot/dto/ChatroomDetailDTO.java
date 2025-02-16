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
public class ChatroomDetailDTO {
    private Long senderId;
    private String senderNickName;
    private Long receiverId;
    private String receiverNickName;
    private Long chatroomId;
    private String receiverProfileUrl;
    private String recentMessage;
    private LocalDateTime createdAt;
}
