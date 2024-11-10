package com.example.springboot.dao;

import com.example.springboot.repository.QuestionOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionOptionDAOImpl implements QuestionOptionDAO {
    private final QuestionOptionRepository questionOptionRepository;
}
