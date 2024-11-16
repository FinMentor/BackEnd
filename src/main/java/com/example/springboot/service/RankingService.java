package com.example.springboot.service;

import com.example.springboot.dto.RankingDTO;

public interface RankingService {
    RankingDTO rankingAll(String term);

    RankingDTO rankingCategory(Long mainCategoryId, String term);
}
