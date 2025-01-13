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
public class PostAllDTO {
    private List<PostDetailsAllDTO> postDetailsAllDTOList;
    private String resultCode;
    private String resultMessage;
}
