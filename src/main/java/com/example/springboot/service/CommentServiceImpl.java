package com.example.springboot.service;

import com.example.springboot.dao.CommentDAO;
import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;
import com.example.springboot.entity.domain.CommentEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.CommentNotFoundException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.repository.MemberRepository;
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

    private final MemberRepository memberRepository;
    private final CommentDAO commentDAO;

    /**
     * 댓글 저장
     * <p>
     * 댓글을 저장하는 메서드입니다.
     *
     * @param commentRequestDTO
     * @return
     */
    @Override
    public CommentResponseDTO save(CommentRequestDTO commentRequestDTO) {
        MemberEntity memberEntity = memberRepository.findById(commentRequestDTO.getMemberId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        CommentEntity commentEntity = CommentEntity.builder()
                .memberEntity(memberEntity)
                .postId(commentRequestDTO.getPostId())
                .content(commentRequestDTO.getContent())
                .build();

        CommentEntity savedComment = commentDAO.save(commentEntity);

        return CommentResponseDTO.builder()
                .commentId(savedComment.getCommentId())
                .memberId(savedComment.getMemberEntity().getId())
                .postId(savedComment.getPostId())
                .content(savedComment.getContent())
                .createdAt(savedComment.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }
    /**
     * 특정 게시글의 모든 댓글 조회
     */
    @Override
    public List<CommentResponseDTO> findAll(Long postId) {
        List<CommentEntity> comments = commentDAO.findAllByPostId(postId);

        return comments.stream()
                .map(commentEntity -> CommentResponseDTO.builder()
                        .commentId(commentEntity.getCommentId())
                        .memberId(commentEntity.getMemberEntity().getId())
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
     */
    @Override
    public CommentResponseDTO update(CommentRequestDTO commentRequestDTO) {
        CommentEntity existingComment = commentDAO.findById(commentRequestDTO.getCommentId())
                .orElseThrow(() -> new CommentNotFoundException(ExceptionCodeEnum.NONEXISTENT_COMMENT));

        CommentEntity updatedCommentEntity = CommentEntity.builder()
                .commentId(existingComment.getCommentId()) // 기존 ID 유지
                .memberEntity(existingComment.getMemberEntity()) // 기존 멤버 유지
                .postId(existingComment.getPostId()) // 기존 게시물 ID 유지
                .content(commentRequestDTO.getContent()) // 새로운 내용 설정
                .build();

        // 업데이트된 객체 저장
        CommentEntity updatedComment = commentDAO.save(updatedCommentEntity);

        return CommentResponseDTO.builder()
                .commentId(updatedComment.getCommentId())
                .memberId(updatedComment.getMemberEntity().getId())
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
     * @param commentId
     */
    @Override
    public void delete(Long commentId) {
        commentDAO.findById(commentId).orElseThrow(() -> new CommentNotFoundException(ExceptionCodeEnum.NONEXISTENT_COMMENT));
        commentDAO.deleteById(commentId);
    }
}
