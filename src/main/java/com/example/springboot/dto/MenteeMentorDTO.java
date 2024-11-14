package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenteeMentorDTO {
    private Long mainCategoryId;
    private Long menteeId;
    private Long mentorId;
    private Integer star;
}
