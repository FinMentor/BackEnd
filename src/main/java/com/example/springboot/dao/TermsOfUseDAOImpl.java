package com.example.springboot.dao;

import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.domain.TermsOfUseEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
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

    /**
     * 이용약관 조회
     * <p>
     * PK로 이용약관 테이블을 조회하는 메소드이다.
     *
     * @param termsOfUseId
     * @return
     */
    @Override
    public Optional<TermsOfUseEntity> findById(Long termsOfUseId) {
        if (termsOfUseId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("termsOfUseId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById termsOfUseId {}", termsOfUseId);

        return termsOfUseRepository.findById(termsOfUseId);
    }

    /**
     * 필수/선택약관구분으로 이용약관 조회
     * <p>
     * 필수/선택약관구분으로 이용약관 테이블을 조회하는 메소드이다.
     *
     * @param required
     * @return
     */
    @Override
    public List<TermsOfUseEntity> findByRequired(ColumnYn required) {
        if (required == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("required는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findByRequired required {}", required);

        return termsOfUseRepository.findByRequired(required);
    }
}
