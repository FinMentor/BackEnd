package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMentorDetailsDTO {
    private Long postId;
    private String title;
    private String postImageUrl;
    private String nickname;
}
