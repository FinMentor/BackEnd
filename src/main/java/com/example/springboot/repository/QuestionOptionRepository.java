package com.example.springboot.repository;

import com.example.springboot.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionOptionRepository extends JpaRepository<QuestionEntity, Integer> {
}
