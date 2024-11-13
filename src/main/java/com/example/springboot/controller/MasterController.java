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

    /**
     * 고수 추천
     * <p>
     * 분야를 기반으로 고수를 추천해주는 메소드이다.
     *
     * @param mainCategoryId
     * @param answerTime
     * @return
     * @throws IOException
     * @throws TasteException
     */
    @GetMapping("/recommend")
    public ResponseResult<MasterRecommendDTO> recommendMaster(@RequestParam long mainCategoryId, @RequestParam String answerTime) throws IOException, TasteException {
        log.info("recommendMaster mainCategoryId : {}, answerTime : {}", mainCategoryId, answerTime);

        return ResponseResult.success(masterService.recommendMaster(mainCategoryId, answerTime));
    }
}
