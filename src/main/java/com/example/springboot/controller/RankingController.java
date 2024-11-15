package com.example.springboot.controller;

import com.example.springboot.dto.RankingAllDTO;
import com.example.springboot.service.RankingService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ranking")
@RequiredArgsConstructor
@Slf4j
public class RankingController {
    private final RankingService rankingService;

    @GetMapping
    public ResponseResult<RankingAllDTO> rankingAll() {
        return ResponseResult.success(rankingService.rankingAll());
    }
}
