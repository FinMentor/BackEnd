package com.example.springboot.controller;

import com.example.springboot.dto.MasterRecommendDTO;
import com.example.springboot.service.MasterService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/master")
@RequiredArgsConstructor
@Slf4j
public class MasterController {
    private final MasterService masterService;

    @GetMapping("/recommend")
    public ResponseResult<MasterRecommendDTO> recommendMaster(@RequestParam long mainCategoryId, @RequestParam String answerTime) throws IOException, TasteException {
        log.info("recommendMaster mainCategoryId : {}, answerTime : {}", mainCategoryId, answerTime);

        return ResponseResult.success(masterService.recommendMaster(mainCategoryId, answerTime));
    }
}
