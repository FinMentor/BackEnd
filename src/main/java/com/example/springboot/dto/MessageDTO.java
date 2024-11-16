package com.example.springboot.dto;

import com.example.springboot.entity.common.util.ColumnYn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Getter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO{
    private Long messageId;
    private Long memberId;
    private Long chatroomId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ColumnYn messageType;
}
