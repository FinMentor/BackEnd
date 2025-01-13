package com.example.springboot.dao;

import com.example.springboot.entity.domain.CommentEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.CommentRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentDAOImpl implements CommentDAO {
    private final CommentRepository commentRepository;

    /**
     * 댓글 저장
     * <p>
     * 댓글을 저장하는 메소드이다.
     *
     * @param commentEntity
     * @return
     */
    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        if (commentEntity.getMemberEntity() == null || commentEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (commentEntity.getPostEntity() == null || commentEntity.getPostEntity().getPostId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (commentEntity.getContent() == null || commentEntity.getContent().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("content는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save commentEntity : {}", commentEntity);

        return commentRepository.save(commentEntity);
    }

    /**
     * 모든댓글 조회
     * <p>
     * 게시아이디로 모든댓글을 조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public List<CommentEntity> findAllByPostId(Long postId) {
        if (postId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAllByPostId postId : {}", postId);

        return commentRepository.findAllByPostId(postId);
    }

    /**
     * 댓글 조회
     * <p>
     * 댓글아이디로 댓글을 조회하는 메소드이다.
     *
     * @param commentId
     * @return
     */
    @Override
    public Optional<CommentEntity> findById(Long commentId) {
        if (commentId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("commentId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAllByPostId commentId : {}", commentId);

        return commentRepository.findById(commentId);
    }

    /**
     * 댓글 삭제
     * <p>
     * 댓글아이디로 댓글을 삭제하는 메소드이다.
     *
     * @param commentId
     */
    @Override
    public void deleteById(Long commentId) {
        if (commentId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("commentId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("deleteById commentId : {}", commentId);

        commentRepository.deleteById(commentId);
    }

}
