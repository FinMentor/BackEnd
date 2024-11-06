package com.example.springboot.repository;

import com.example.springboot.entity.InterestCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestCategoryRepository extends JpaRepository<InterestCategoryEntity, Integer> {

}
