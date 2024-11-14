package com.example.springboot.controller;

import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.service.PostService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    /**
     * 커뮤니티 게시글 작성 후 저장
     * @param postDTO 클라이언트에서 전달받은 게시글 데이터
     * @return 저장된 게시글 데이터와 함께 성공 응답 반환
     */
    @PostMapping("/save")
    public ResponseResult<PostResponseDTO> save(@RequestBody PostRequestDTO postDTO) {
        log.info("save PostDTO : {}", postDTO);
        return ResponseResult.success(postService.save(postDTO));
    }
    /**
     * 게시글 ID로 게시글 조회 , 해당 게시글 조회수 업데이트
     * @param postId 조회할 게시글의 ID
     * @return 조회된 게시글 데이터와 함께 성공 응답 반환
     */
    @GetMapping("/{postId}")
    public ResponseResult<PostResponseDTO> findById(@RequestParam Long postId) {
        log.info("find post by id : {}", postId);
        postService.updateViewCount(postId);
        return ResponseResult.success(postService.findById(postId));
    }
    /**
     * 모든 게시글 조회
     * @return 모든 게시글 리스트와 함께 성공 응답 반환
     */
    @GetMapping
    public ResponseResult<List<PostResponseDTO>> findAll() {
        log.info("find all posts");
        return ResponseResult.success(postService.findAll());
    }
}
