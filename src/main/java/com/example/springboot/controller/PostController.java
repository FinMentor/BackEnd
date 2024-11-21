package com.example.springboot.controller;

import com.example.springboot.dto.*;
import com.example.springboot.service.PostService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * @param postId
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult<PostAllDTO> postDetails(@RequestParam Long postId) {
        log.info("findById postId : {}", postId);

        return ResponseResult.success(postService.postDetails(postId));
    }

    /**
     * 게시글리스트 조회
     * <p>
     * 게시글리스트를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @return
     */
    @GetMapping
    public ResponseResult<PostDTO> findAll(@RequestParam Long mainCategoryId) {
        log.info("findAll mainCategoryId : {}", mainCategoryId);

        return ResponseResult.success(postService.findAll(mainCategoryId));
    }

    /**
     * 고수 게시글리스트 조회
     * <p>
     * 고수의 게시글리스트를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @return
     */
    @GetMapping("/mentor")
    public ResponseResult<PostMentorDTO> mentorPostAll(@RequestParam Long mainCategoryId) {
        log.info("mentorPostAll mainCategoryId : {}", mainCategoryId);

        return ResponseResult.success(postService.mentorPostAll(mainCategoryId));
    }

    /**
     * 게시글 수정
     * <p>
     * 클라이언트에서 전달받은 게시글 데이터를 업데이트하는 메소드이다.
     *
     * @param postRequestDTO 클라이언트에서 전달받은 업데이트할 게시글 데이터
     * @return 업데이트된 게시글 데이터와 함께 성공 응답 반환
     */
    @PutMapping("/update")
    public ResponseResult<PostResponseDTO> update(@RequestBody PostRequestDTO postRequestDTO) {
        log.info("update postRequestDTO : {}", postRequestDTO);

        return ResponseResult.success(postService.update(postRequestDTO));
    }

    /**
     * 게시글 삭제
     * <p>
     * 게시아이디로 게시글을 삭제하는 메소드이다.
     *
     * @param postId 삭제할 게시글의 ID
     * @return 삭제 성공 응답 반환
     */
    @DeleteMapping("/delete")
    public ResponseResult<PostResponseDTO> delete(@RequestParam Long postId) {
        log.info("delete postId : {}", postId);

        return ResponseResult.success(postService.delete(postId));
    }
}
