package com.example.springboot.repository;

import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.domain.TermsOfUseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermsOfUseRepository extends JpaRepository<TermsOfUseEntity, Long> {
    List<TermsOfUseEntity> findByRequired(ColumnYn required);
}
