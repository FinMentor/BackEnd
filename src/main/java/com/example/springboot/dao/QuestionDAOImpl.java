package com.example.springboot.dao;

import com.example.springboot.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionDAOImpl implements QuestionDAO {
    private final QuestionRepository questionRepository;
}
