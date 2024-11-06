package com.example.springboot.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "example")
public class ExampleEntity {
    @Id
    @Column(name = "example")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String example;

    public static ExampleEntity createExampleEntity(String example) {
        return new ExampleEntity(example);
    }
}
