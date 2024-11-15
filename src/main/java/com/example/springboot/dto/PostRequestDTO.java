package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostRequestDTO {
    private String id;
    private Long mainCategoryId;
    private String title;
    private String content;
}
