package com.example.springboot.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatRoomDTO {
    private Long chatRoomId;
    private String subject;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
