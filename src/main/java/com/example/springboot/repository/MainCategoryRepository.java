package com.example.springboot.repository;

import com.example.springboot.entity.domain.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Long> {

}
