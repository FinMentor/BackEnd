package com.example.springboot.service;

import com.example.springboot.dto.InterestCategoryDTO;
import com.example.springboot.repository.InterestCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterestCategoryServiceImpl implements InterestCategoryService {
    private final InterestCategoryRepository interestCategoryRepository;

    // 메인 카테고리 전체 불러오기
    @Override
    public List<InterestCategoryDTO> getAllMainCategories() {
        return interestCategoryRepository.getAllMainCategories();
    }

    // 사용자가 등록한 관심 세부 카테고리 불러오기
    @Override
    public List<InterestCategoryDTO> getMemberSubCategories(String memberId) {
        return interestCategoryRepository.getMemberSubCategories(memberId);
    }

    // 사용자가 등록한 관심 세부 카테고리 삭제
    @Override
    public int deleteSubCategory(String memberId, int subCategoryId) {
        return interestCategoryRepository.deleteSubCategory(memberId, subCategoryId);
    }

    // 메인 카테고리에 따른 세부 카테고리 목록 불러오기
    @Override
    public List<InterestCategoryDTO> getSubCategoriesByMainCategoryId(int mainCategoryId) {
        return interestCategoryRepository.getSubCategoriesByMainCategoryId(mainCategoryId);
    }

    // 사용자가 선택한 세부 카테고리 저장
    @Override
    public void saveInterestCategory(String memberId, int subCategoryId) {
        interestCategoryRepository.saveInterestCategory(memberId, subCategoryId);
    }
}
