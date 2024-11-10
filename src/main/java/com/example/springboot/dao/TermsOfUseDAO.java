package com.example.springboot.dao;

import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.domain.TermsOfUseEntity;

import java.util.List;
import java.util.Optional;

public interface TermsOfUseDAO {
    Optional<TermsOfUseEntity> findById(Long termsOfUseId);

    List<TermsOfUseEntity> findByRequired(ColumnYn required);
}
