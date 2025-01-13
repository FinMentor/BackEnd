package com.example.springboot.service;

import com.example.springboot.dao.MainCategoryDAO;
import com.example.springboot.dto.CategoryDTO;
import com.example.springboot.dto.MainCategoryDTO;
import com.example.springboot.exception.FailGetMainCategoryException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final MainCategoryDAO mainCategoryDAO;

    /**
     * 카테고리리스트 조회
     * <p>
     * 카테고리 전체를 조회하는 메소드이다.
     *
     * @return
     */
    @Override
    public CategoryDTO categoryAll() {
        // 메인카테고리 조회
        List<MainCategoryDTO> mainCategoryDTOList = mainCategoryDAO.findAll().stream()
                .map(mainCategoryEntity -> {
                    return MainCategoryDTO.builder()
                            .mainCategoryId(mainCategoryEntity.getMainCategoryId())
                            .mainCategoryName(mainCategoryEntity.getMainCategoryName()).build();
                })
                .toList();

        if (mainCategoryDTOList.isEmpty()) {
            throw new FailGetMainCategoryException(ExceptionCodeEnum.NONEXISTENT_CATEGORY);
        }

        return CategoryDTO.builder()
                .mainCategoryDTOList(mainCategoryDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
