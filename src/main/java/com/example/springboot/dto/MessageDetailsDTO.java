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
public class MessageDetailsDTO {
    private Long messageId;
    private Long memberId;
    private Long chatroomId;
    private String content;
    private Character messageType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
