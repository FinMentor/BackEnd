package com.example.springboot.controller;

import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;
import com.example.springboot.service.CommentService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 저장 및 댓글리스트 조회
     * <p>
     * 댓글을 저장하고 댓글리스트를 조회하는 메소드이다.
     *
     * @param commentRequestDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseResult<List<CommentResponseDTO>> save(@RequestBody CommentRequestDTO commentRequestDTO) {
        log.info("save commentRequestDTO : {}", commentRequestDTO);
        // 댓글 저장
        commentService.save(commentRequestDTO);

        return ResponseResult.success(commentService.findAll(commentRequestDTO.getPostId()));
    }

    /**
     * 게시글 전체댓글 조회
     * <p>
     * 게시글의 전체댓글을 조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @GetMapping("/all")
    public ResponseResult<List<CommentResponseDTO>> getCommentsByPostId(@RequestParam Long postId) {
        log.info("getCommentsByPostId postId : {}", postId);

        return ResponseResult.success(commentService.findAll(postId));
    }

    /**
     * 댓글 수정
     * <p>
     * 댓글을 수정하는 메소드이다.
     *
     * @param commentRequestDTO
     * @return
     */
    @PutMapping("/update")
    public ResponseResult<CommentResponseDTO> update(@RequestBody CommentRequestDTO commentRequestDTO) {
        log.info("update commentRequestDTO : {}", commentRequestDTO);

        return ResponseResult.success(commentService.update(commentRequestDTO));
    }

    /**
     * 댓글 삭제
     * <p>
     * 댓글을 삭제하는 메소드이다.
     *
     * @param commentId
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult<CommentResponseDTO> delete(@RequestParam Long commentId) {
        log.info("delete commentId : {}", commentId);

        return ResponseResult.success(commentService.delete(commentId));
    }
}
