package com.example.springboot.dao;

import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.PostRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostDAOImpl implements PostDAO {
    private final PostRepository postRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public PostEntity save(PostEntity postEntity) {
        if (postEntity.getTitle() == null || postEntity.getTitle().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("title은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
        if (postEntity.getContent() == null || postEntity.getContent().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("content는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
        if (postEntity.getMemberId() == null || postEntity.getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidation(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
        log.info("save postEntity: {}", postEntity);
        entityManager.persist(postEntity);
        return postRepository.save(postEntity);
    }

    @Override
    public Optional<PostEntity> findById(Long postId) {
        PostEntity postEntity = entityManager.find(PostEntity.class, postId);
        return Optional.ofNullable(postEntity);
    }
}
