package com.example.springboot.controller;

import com.example.springboot.dto.PostDTO;
import com.example.springboot.service.PostService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    // 글 작성
    @PostMapping("/save")
    public ResponseResult<String> save(@RequestBody PostDTO postDTO) {
        if (log.isInfoEnabled()) {
            log.info("save PostDTO : {}", postDTO);
        }

        return ResponseResult.success(postService.save(postDTO));
    }
}
