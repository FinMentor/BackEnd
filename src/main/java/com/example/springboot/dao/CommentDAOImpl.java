package com.example.springboot.dao;

import com.example.springboot.entity.domain.CommentEntity;
import com.example.springboot.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentDAOImpl implements CommentDAO {
    private final CommentRepository commentRepository;

    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }
    @Override
    public List<CommentEntity> findAllByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
    @Override
    public Optional<CommentEntity> findById(Long commentId) {
        return commentRepository.findById(commentId);
    }
    @Override
    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
