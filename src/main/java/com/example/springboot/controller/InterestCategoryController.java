package com.example.springboot.controller;

import com.example.springboot.dto.InterestCategoryDTO;
import com.example.springboot.service.InterestCategoryService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class InterestCategoryController {
    private final InterestCategoryService interestCategoryService;

    // 메인 카테고리 목록 불러오기
    @GetMapping("")
    public ResponseResult<List<InterestCategoryDTO>> getAllMainCategories() {
        log.info("메인 카테고리 전체 조회 요청");
        return ResponseResult.success(interestCategoryService.getAllMainCategories());
    }

    // 사용자가 등록한 관심 세부 카테고리 불러오기
    @GetMapping("/interest")
    public ResponseResult<List<InterestCategoryDTO>> getMemberSubCategories(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("사용자가 등록한 관심 세부 카테고리 조회 요청 - memberId: {}", userDetails.getUsername());
        return ResponseResult.success(interestCategoryService.getMemberSubCategories(userDetails.getUsername()));
    }

    // 세부 카테고리 삭제
    @DeleteMapping("/{memberId}/{subCategoryId}")
    public ResponseResult<String> deleteSubCategory(@PathVariable String memberId, @PathVariable int subCategoryId) {
        log.info("카테고리 삭제 요청 - memberId: {}, subCategoryId: {}", memberId, subCategoryId);
        int result = interestCategoryService.deleteSubCategory(memberId, subCategoryId);
        String message = result > 0 ? "삭제 성공" : "삭제 실패";
        log.info("카테고리 삭제 결과 - message: {}", message);
        return ResponseResult.success(message);
    }

    // 메인 카테고리에 따른 세부 카테고리 목록 불러오기
    @GetMapping("/subcategory/{mainCategoryId}")
    public ResponseResult<List<InterestCategoryDTO>> getSubCategoriesByMainCategoryId(@PathVariable int mainCategoryId) {
        log.info("세부 카테고리 조회 요청 - mainCategoryId: {}", mainCategoryId);
        return ResponseResult.success(interestCategoryService.getSubCategoriesByMainCategoryId(mainCategoryId));
    }

    // 사용자가 선택한 세부 카테고리 저장
    @PostMapping("/interest/{subCategoryId}")
    public ResponseResult<String> saveInterestCategory(@AuthenticationPrincipal UserDetails userDetails,
                                                       @PathVariable int subCategoryId) {
        String memberId = userDetails.getUsername();
        log.info("관심 항목 저장 요청 - memberId: {}, subCategoryId: {}", memberId, subCategoryId);
        interestCategoryService.saveInterestCategory(memberId, subCategoryId);
        return ResponseResult.success("관심 항목에 추가되었습니다.");
    }
}
