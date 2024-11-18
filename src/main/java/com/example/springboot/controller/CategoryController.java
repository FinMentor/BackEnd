package com.example.springboot.controller;

import com.example.springboot.dto.CategoryDTO;
import com.example.springboot.service.CategoryService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리리스트 조회
     * <p>
     * 카테고리 전체를 조회하는 메소드이다.
     *
     * @return
     */
    @GetMapping("/all")
    public ResponseResult<CategoryDTO> categoryAll() {
        return ResponseResult.success(categoryService.categoryAll());
    }
}
