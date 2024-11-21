package com.example.springboot.dao;

import com.example.springboot.entity.domain.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
    PostEntity save(PostEntity postEntity);

    Optional<PostEntity> findById(Long postId);

    List<Object[]> findAll(String postType);

    List<Object[]> findAllByMainCategoryId(Long mainCategoryId, String postType);

    List<PostEntity> findAllMentor(String postType);

    List<PostEntity> findAllMentorByMainCategoryId(Long mainCategoryId, String postType);

    void updateViewCount(Long postId);

    PostEntity update(PostEntity postEntity);

    void deleteById(Long postId);
}
