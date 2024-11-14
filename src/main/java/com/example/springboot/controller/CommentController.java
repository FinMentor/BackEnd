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
     * 댓글 저장 및 해당 게시글의 모든 댓글 목록 반환
     */
    @PostMapping("/save")
    public ResponseResult save(@RequestBody CommentRequestDTO commentRequestDTO) {
        log.info("save comment: {}", commentRequestDTO);
        // 댓글 저장
        commentService.save(commentRequestDTO);
        // 저장 후 전체 댓글 목록 가져오기
        List<CommentResponseDTO> comments = commentService.findAll(commentRequestDTO.getPostId());

        // 업데이트된 댓글 목록 반환
        return ResponseResult.success(comments);
    }

    /**
     * 해당 게시글의 모든 댓글 불러오기
     */
    @GetMapping("/getComments")
    public ResponseResult<List<CommentResponseDTO>> getCommentsByPostId(@RequestParam Long postId) {
        log.info("Fetching comments for post ID: {}", postId);

        // 특정 게시글의 모든 댓글 조회
        List<CommentResponseDTO> comments = commentService.findAll(postId);

        return ResponseResult.success(comments);
    }

    /**
     * 댓글 수정
     */
    @PutMapping("/update")
    public ResponseResult<CommentResponseDTO> update(@RequestBody CommentRequestDTO commentRequestDTO) {
        log.info("Updating comment: {}", commentRequestDTO);

        // 댓글 수정
        CommentResponseDTO updatedComment = commentService.update(commentRequestDTO);

        return ResponseResult.success(updatedComment);
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/delete")
    public ResponseResult<Void> delete(@RequestParam Long commentId) {
        log.info("Deleting comment with ID: {}", commentId);

        // 댓글 삭제
        commentService.delete(commentId);

        return ResponseResult.success(null);
    }
}
