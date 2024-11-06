package com.example.springboot.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleResponseDTO {
    private String example;

    public static ExampleResponseDTO of(ExampleDTO exampleDTO) {
        return new ExampleResponseDTO(exampleDTO.getExample());
    }
}
