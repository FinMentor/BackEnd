package com.example.springboot.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TermsOfUseDTO {
    private Long termOfUseId;          // 이용 약관 ID
    private String title;              // 제목
    private String content;            // 내용
    private Character required;           // 필수 여부 (0: 비필수, 1: 필수)
}
