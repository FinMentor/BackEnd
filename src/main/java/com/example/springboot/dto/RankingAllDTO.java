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
public class RankingAllDTO {
    private List<MentorOfThreeDTO> mentorOfThreeDTOList;
    private String resultCode;
    private String resultMessage;
}
