package com.example.springboot.example.dto;

import com.example.springboot.example.entity.ExampleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDTO {
    private String example;

    public static ExampleDTO of(ExampleEntity exampleEntity) {
        return new ExampleDTO(exampleEntity.getExample());
    }
}
