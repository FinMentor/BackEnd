package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostRequestDTO {
    private Long postId;
    private Long memberId;
    private Long mainCategoryId;
    private String title;
    private String content;
}
