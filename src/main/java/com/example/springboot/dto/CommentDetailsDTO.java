package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDetailsDTO {
    private Long commentId;
    private String mainCategoryName;
    private String nickname;
    private String profileImageUrl;
    private String content;
    private String createdAt;
}
