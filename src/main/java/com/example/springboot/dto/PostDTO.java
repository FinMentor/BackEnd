package com.example.springboot.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostDTO {
    private Long postId;
    private String memberId;
    private Long mainCategoryId;
    private String title;
    private String content;
    private Long viewCount;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
