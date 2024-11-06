package com.example.springboot.repository;

import com.example.springboot.dto.InterestCategoryDTO;
import com.example.springboot.entity.InterestCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterestCategoryRepository extends JpaRepository<InterestCategoryEntity, Integer> {
    // 메인 카테고리 전체 불러오기
    List<InterestCategoryDTO> getAllMainCategories();

    // 사용자가 등록한 관심 세부 카테고리 불러오기
    List<InterestCategoryDTO> getMemberSubCategories(String memberId);

    // 사용자가 등록한 관심 세부 카테고리 삭제
    int deleteSubCategory(@Param("memberId") String memberId, @Param("subCategoryId") int subCategoryId);

    // 메인 카테고리에 따른 세부 카테고리 목록 불러오기
    List<InterestCategoryDTO> getSubCategoriesByMainCategoryId(@Param("mainCategoryId") int mainCategoryId);

    // 사용자가 선택한 세부 카테고리 저장
    int saveInterestCategory(@Param("memberId") String memberId, @Param("subCategoryId") int subCategoryId);
}
