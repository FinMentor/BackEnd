package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
