package com.example.springboot.repository;

import com.example.springboot.entity.TermsOfUseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsOfUseRepository extends JpaRepository<TermsOfUseEntity, Integer> {
    List<TermsOfUseEntity> selectListTermsOfUseByRequired(String required);

    List<TermsOfUseEntity> selectListTermsOfUse();
}
