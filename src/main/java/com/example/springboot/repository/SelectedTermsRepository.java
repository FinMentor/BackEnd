package com.example.springboot.repository;

import com.example.springboot.entity.SelectedTermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedTermsRepository extends JpaRepository<SelectedTermsEntity, Integer> {
}
