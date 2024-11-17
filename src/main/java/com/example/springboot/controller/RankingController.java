package com.example.springboot.controller;

import com.example.springboot.dto.RankingDTO;
import com.example.springboot.service.RankingService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {
    private final RankingService rankingService;

    /**
     * 전체랭킹 조회
     * <p>
     * 멘토전체랭킹을 조회하는 메소드이다.
     *
     * @param term
     * @return
     */
    @GetMapping
    public ResponseResult<RankingDTO> rankingAll(@RequestParam(required = false) String term) {
        log.info("rankingAll term : {}", term);

        return ResponseResult.success(rankingService.rankingAll(term));
    }

    /**
     * 전체랭킹 조회 By 카테고리
     * <p>
     * 카테고리별로 전체랭킹을 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @param term
     * @return
     */
    @GetMapping("/category")
    public ResponseResult<RankingDTO> rankingCategory(@RequestParam Long mainCategoryId, @RequestParam String term) {
        log.info("rankingCategory mainCategoryId : {}, term : {}", mainCategoryId, term);

        return ResponseResult.success(rankingService.rankingCategory(mainCategoryId, term));
    }
}
