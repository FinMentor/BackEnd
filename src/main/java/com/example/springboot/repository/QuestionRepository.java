package com.example.springboot.repository;

import com.example.springboot.entity.QuestionEntity;
import com.example.springboot.entity.QuestionOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    //특정 질문 조회
    QuestionEntity selectSurveyQuestion(@Param("questionId") long questionId);

    // 특정 질문에 대한 옵션들 조회
    List<QuestionOptionEntity> selectOptionsByQuestionId(@Param("questionId") long questionId);

    int getTotalQuestionsCount();
}
