package com.example.springboot.repository;

import com.example.springboot.entity.TermsOfUseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TermsOfUseRepository extends JpaRepository<TermsOfUseEntity, Integer> {
    Optional<TermsOfUseEntity> findByRequired(Character required);
}
