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

    /**
     * 수정할 게시글 불러오기
     * <p>
     * 게시글 아이디로 수정할 게시글 데이터를 불러오는 메소드이다.
     *
     * @param postId 수정할 게시글의 ID
     * @return 수정할 게시글 데이터와 함께 성공 응답 반환
     */
    @GetMapping("/update")
    public ResponseResult<PostResponseDTO> getPostForUpdate(@RequestParam Long postId) {
        log.info("getPostForUpdate postId : {}", postId);
        return ResponseResult.success(postService.findById(postId));
    }
    /**
     * 게시글 업데이트
     * <p>
     * 클라이언트에서 전달받은 게시글 데이터를 기반으로 게시글을 업데이트하는 메소드이다.
     *
     * @param postRequestDTO 클라이언트에서 전달받은 업데이트할 게시글 데이터
     * @return 업데이트된 게시글 데이터와 함께 성공 응답 반환
     */
    @PutMapping("/update")
    public ResponseResult<PostResponseDTO> update(@RequestBody PostRequestDTO postRequestDTO) {
        log.info("postRequestDTO : {}", postRequestDTO);
        return ResponseResult.success(postService.update(postRequestDTO));
    }
    /**
     * 게시글 삭제
     * <p>
     * 게시글 아이디로 게시글을 삭제하는 메소드이다.
     *
     * @param postId 삭제할 게시글의 ID
     * @return 삭제 성공 응답 반환
     */
    @DeleteMapping("/delete")
    public ResponseResult<Void> delete(@RequestParam Long postId) {
        log.info("delete postId : {}", postId);
        postService.delete(postId);
        return ResponseResult.success(null);
    }
}
