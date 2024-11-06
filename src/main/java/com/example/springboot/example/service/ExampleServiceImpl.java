package com.example.springboot.example.service;

import com.example.springboot.example.dto.ExampleDTO;
import com.example.springboot.example.entity.ExampleEntity;
import com.example.springboot.example.repository.ExampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ExampleServiceImpl implements ExampleService {
    private final ExampleRepository exampleRepository;

    @Override
    public ExampleDTO getExample(String example) {
        ExampleEntity exampleEntity = exampleRepository.findByExample(example);

        return ExampleDTO.of(exampleEntity);
    }
}
