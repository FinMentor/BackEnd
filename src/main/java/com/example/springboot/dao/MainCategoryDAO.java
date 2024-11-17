package com.example.springboot.dao;

import com.example.springboot.entity.domain.MainCategoryEntity;

import java.util.Optional;

public interface MainCategoryDAO {
    Optional<MainCategoryEntity> findById(Long mainCategoryId);
}
