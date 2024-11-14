package com.example.springboot.dao;

import com.example.springboot.entity.domain.CommentEntity;

public interface CommentDAO {
    CommentEntity save(CommentEntity commentEntity);
}
