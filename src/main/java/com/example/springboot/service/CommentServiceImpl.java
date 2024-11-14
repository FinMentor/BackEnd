package com.example.springboot.service;

import com.example.springboot.dao.CommentDAO;
import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;
import com.example.springboot.entity.domain.CommentEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
                .memberId(savedComment.getMemberEntity().getMemberId())
                .postId(savedComment.getPostId())
                .content(savedComment.getContent())
                .createdAt(savedComment.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }
}
