package com.example.springboot.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuestionDTO {
    private Long questionId;
    private String content;
    private List<QuestionOptionDTO> options; // 클라이언트에 보낼 데이터만 포함
}
