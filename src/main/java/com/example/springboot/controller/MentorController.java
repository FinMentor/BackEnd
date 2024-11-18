package com.example.springboot.controller;

import com.example.springboot.dto.MentorRecommendDTO;
import com.example.springboot.service.MentorService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/mentor")
@RequiredArgsConstructor
@Slf4j
public class MentorController {
    private final MentorService masterService;

    /**
     * 고수 추천
     * <p>
     * 분야를 기반으로 고수를 추천해주는 메소드이다.
     *
     * @param userDetails
     * @param mainCategoryId
     * @return
     * @throws IOException
     * @throws TasteException
     */
    @GetMapping("/recommend")
    public ResponseResult<MentorRecommendDTO> recommendMentor(@AuthenticationPrincipal UserDetails userDetails, @RequestParam Long mainCategoryId) throws IOException, TasteException {
        log.info("recommendMaster userDetails : {}, mainCategoryId : {}", userDetails, mainCategoryId);

        return ResponseResult.success(masterService.recommendMentor(userDetails.getUsername(), mainCategoryId));
    }
}
