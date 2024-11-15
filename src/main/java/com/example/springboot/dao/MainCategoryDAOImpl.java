package com.example.springboot.dao;

import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MainCategoryRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainCategoryDAOImpl implements MainCategoryDAO {
    private final MainCategoryRepository mainCategoryRepository;

    @Override
    public Optional<MainCategoryEntity> findById(Long mainCategoryId) {
        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById mainCategoryId : {}", mainCategoryId);

        return mainCategoryRepository.findById(mainCategoryId);
    }
}
