package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionOptionDTO {
    private Long questionOptionId;
    private String content;
    private double score;
}
