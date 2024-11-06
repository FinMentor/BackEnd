package com.example.springboot.controller;

import com.example.springboot.dto.TermsOfUseDTO;
import com.example.springboot.service.TermsOfUseService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/terms-of-use")
@RequiredArgsConstructor
@Slf4j
public class TermsOfUseController {
    private final TermsOfUseService termsOfUseService;

    @GetMapping
    public ResponseResult<List<TermsOfUseDTO>> getTermsOfUse() {
        return ResponseResult.success(termsOfUseService.getTermsOfUse());
    }
}
