package com.example.springboot.dao;

import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.domain.TermsOfUseEntity;
import com.example.springboot.exception.ErrorRequiredValueValidation;
import com.example.springboot.repository.TermsOfUseRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TermsOfUseDAOImpl implements TermsOfUseDAO {
    private final TermsOfUseRepository termsOfUseRepository;

    @Override
    public Optional<TermsOfUseEntity> findById(Long termsOfUseId) {
        if (termsOfUseId == null) {
            throw new ErrorRequiredValueValidation(new StringBuilder("termsOfUseId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById termsOfUseId {}", termsOfUseId);

        return termsOfUseRepository.findById(termsOfUseId);
    }

    @Override
    public List<TermsOfUseEntity> findByRequired(ColumnYn required) {
        if (required == null) {
            throw new ErrorRequiredValueValidation(new StringBuilder("required는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findByRequired required {}", required);

        return termsOfUseRepository.findByRequired(required);
    }
}
