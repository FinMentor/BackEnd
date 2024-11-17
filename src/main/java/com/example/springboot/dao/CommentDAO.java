package com.example.springboot.dao;

import com.example.springboot.entity.domain.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
    CommentEntity save(CommentEntity commentEntity);

    List<CommentEntity> findAllByPostId(Long postId);

    Optional<CommentEntity> findById(Long commentId);

    void deleteById(Long commentId);
}
