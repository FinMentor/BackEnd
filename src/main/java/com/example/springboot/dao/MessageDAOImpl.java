package com.example.springboot.dao;

import com.example.springboot.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageDAOImpl implements MessageDAO {
    private final MessageRepository messageRepository;
}