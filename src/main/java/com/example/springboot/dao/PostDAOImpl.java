package com.example.springboot.dao;

import com.example.springboot.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostDAOImpl implements PostDAO {
    private final PostRepository postRepository;
}
