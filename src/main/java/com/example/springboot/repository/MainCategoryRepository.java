package com.example.springboot.repository;

import com.example.springboot.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Integer> {
}
