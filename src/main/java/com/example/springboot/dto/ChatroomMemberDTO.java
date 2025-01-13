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
public class ChatroomMemberDTO {
    private Long chatroomId;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
