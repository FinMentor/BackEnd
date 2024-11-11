package com.example.springboot.dao;

import com.example.springboot.entity.domain.PostEntity;

import java.util.Optional;

public interface PostDAO {
    PostEntity save(PostEntity postEntity);
    Optional<PostEntity> findById(Long postId);
}
