package com.example.springboot.dao;

import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MainCategoryRepository;
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
public class MainCategoryDAOImpl implements MainCategoryDAO {
    private final MainCategoryRepository mainCategoryRepository;

    /**
     * 메인카테고리 조회
     * <p>
     * 메인카테고리아이디로 메인카테고리를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @return
     */
    @Override
    public Optional<MainCategoryEntity> findById(Long mainCategoryId) {
        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById mainCategoryId : {}", mainCategoryId);

        return mainCategoryRepository.findById(mainCategoryId);
    }

    /**
     * 메인카테고리리스트 조회
     * <p>
     * 메인카테고리 전체를 조회하는 메소드이다.
     *
     * @return
     */
    @Override
    public List<MainCategoryEntity> findAll() {
        return mainCategoryRepository.findAll();
    }
}
