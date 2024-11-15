package com.example.springboot.service;

import com.example.springboot.dao.CommentDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;
import com.example.springboot.entity.domain.CommentEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.CommentNotFoundException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final MemberDAO memberDAO;
    private final CommentDAO commentDAO;

    /**
     * 댓글 저장
     * <p>
     * 댓글을 저장하는 메서드이다.
     *
     * @param commentRequestDTO
     * @return
     */
    @Override
    public CommentResponseDTO save(CommentRequestDTO commentRequestDTO) {
        MemberEntity memberEntity = memberDAO.findById(commentRequestDTO.getId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        CommentEntity savedComment = commentDAO.save(CommentEntity.builder()
                .memberEntity(memberEntity)
                .postId(commentRequestDTO.getPostId())
                .content(commentRequestDTO.getContent())
                .build());

        return CommentResponseDTO.builder()
                .commentId(savedComment.getCommentId())
                .id(savedComment.getMemberEntity().getId())
                .postId(savedComment.getPostId())
                .content(savedComment.getContent())
                .createdAt(savedComment.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    /**
     * 게시글 전체댓글 조회
     * <p>
     * 게시글에 전체댓글을 조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public List<CommentResponseDTO> findAll(Long postId) {
        return commentDAO.findAllByPostId(postId).stream()
                .map(commentEntity -> CommentResponseDTO.builder()
                        .commentId(commentEntity.getCommentId())
                        .id(commentEntity.getMemberEntity().getId())
                        .postId(commentEntity.getPostId())
                        .content(commentEntity.getContent())
                        .createdAt(commentEntity.getCreatedAt())
                        .updatedAt(commentEntity.getUpdatedAt())
                        .resultCode(ResultCodeEnum.SUCCESS.getValue())
                        .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 댓글 수정
     * <p>
     * 댓글을 수정하는 메소드이다.
     *
     * @param commentRequestDTO
     * @return
     */
    @Override
    public CommentResponseDTO update(CommentRequestDTO commentRequestDTO) {
        CommentEntity existingComment = commentDAO.findById(commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ExceptionCodeEnum.NONEXISTENT_COMMENT));

        // 업데이트된 객체 저장
        CommentEntity updatedComment = commentDAO.save(CommentEntity.builder()
                .commentId(existingComment.getCommentId()) // 기존 ID 유지
                .memberEntity(existingComment.getMemberEntity()) // 기존 멤버 유지
                .postId(existingComment.getPostId()) // 기존 게시물 ID 유지
                .content(commentRequestDTO.getContent()) // 새로운 내용 설정
                .build());

        return CommentResponseDTO.builder()
                .commentId(updatedComment.getCommentId())
                .id(updatedComment.getMemberEntity().getId())
                .postId(updatedComment.getPostId())
                .content(updatedComment.getContent())
                .createdAt(updatedComment.getCreatedAt())
                .updatedAt(updatedComment.getUpdatedAt())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    /**
     * 댓글 삭제
     * <p>
     * 댓글을 삭제하는 메소드이다.
     *
     * @param commentId
     * @return
     */
    @Override
    public CommentResponseDTO delete(Long commentId) {
        // 댓글 조회
        commentDAO.findById(commentId).orElseThrow(() -> new CommentNotFoundException(ExceptionCodeEnum.NONEXISTENT_COMMENT));

        // 댓글 삭제
        commentDAO.deleteById(commentId);

        return CommentResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
