package com.example.springboot.controller;

import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.service.CommentService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody CommentRequestDTO commentRequestDTO) {
        log.info("save comment: {}", commentRequestDTO);
        return ResponseResult.success(commentService.save(commentRequestDTO));
    }
}
