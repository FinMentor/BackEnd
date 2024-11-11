package com.example.springboot.controller;

import com.example.springboot.dto.PostDTO;
import com.example.springboot.service.PostService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    /**
     * 커뮤니티 게시글 작성 후 저장
     * @param postDTO
     * @return
     */

    @PostMapping("/save")
    public ResponseResult<PostDTO> save(@RequestBody PostDTO postDTO) {
        log.info("save PostDTO : {}", postDTO);
        return ResponseResult.success(postService.save(postDTO));
    }
    @GetMapping("/{id}")
    public ResponseResult<PostDTO> findById(@PathVariable Long postId) {
        log.info("find post by id : {}", postId);
        return ResponseResult.success(postService.getPostById(postId));
    }
}
