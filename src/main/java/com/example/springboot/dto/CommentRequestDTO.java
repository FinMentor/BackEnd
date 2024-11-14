package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentRequestDTO {
    private String memberId;
    private Long postId;
    private String content;
}
