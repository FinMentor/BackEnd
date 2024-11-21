package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsAllDTO {
    private Long postId;
    private String nickname;
    private String profileImageUrl;
    private String title;
    private String content;
    private Long viewCount;
    private Long likeCount;
    private Long commentCount;
    private String createdAt;
    private List<CommentDetailsDTO> commentDetailsDTOList;
}
