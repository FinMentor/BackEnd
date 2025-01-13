package com.example.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFindIdDTO {
    private List<MemberIdDTO> memberIdDTOList;
    private String resultCode;
    private String resultMessage;
}
