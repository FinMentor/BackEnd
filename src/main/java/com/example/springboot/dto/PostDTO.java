package com.example.springboot.dto;

import lombok.*;

import java.sql.Timestamp;

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
    private int viewCOUNT;
    private int likeCOUNT;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
