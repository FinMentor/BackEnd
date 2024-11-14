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
     * 커뮤니티 게시글 저장
     * <p>
     * 클라이언트에서 전달받은 게시글 데이터를 저장하는 메소드이다.
     *
     * @param postRequestDTO 클라이언트에서 전달받은 게시글 데이터
     * @return 저장된 게시글 데이터와 함께 성공 응답 반환
     */
    @PostMapping("/save")
    public ResponseResult<PostResponseDTO> save(@RequestBody PostRequestDTO postRequestDTO) {
        log.info("save postRequestDTO : {}", postRequestDTO);

        return ResponseResult.success(postService.save(postRequestDTO));
    }

    /**
     * 게시글 조회 및 조회수 업데이트
     * <p>
     * 게시글아이디로 조회와 조회수를 업데이트를 하는 메소드이다.
     *
     * @param postId 조회할 게시글의 ID
     * @return 조회된 게시글 데이터와 함께 성공 응답 반환
     */
    @GetMapping("/detail")
    public ResponseResult<PostResponseDTO> findById(@RequestParam Long postId) {
        log.info("findById postId : {}", postId);

        return ResponseResult.success(postService.findById(postId));
    }

    /**
     * 게시글리스트 조회
     * <p>
     * 게시글리스트를 조회하는 메소드이다.
     *
     * @return 게시글리스트와 함께 성공 응답 반환
     */
    @GetMapping
    public ResponseResult<List<PostResponseDTO>> findAll() {
        return ResponseResult.success(postService.findAll());
    }
}
