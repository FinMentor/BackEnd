package com.example.springboot.example.repository;

import com.example.springboot.example.entity.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<ExampleEntity, Integer> {
    ExampleEntity findByExample(String example);
}
