package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsDTO {
    private Long postId;
    private String title;
    private String content;
    private Long likeCount;
    private Long commentCount;
    private String createdAt;
}
