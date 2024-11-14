package com.example.springboot.dao;

import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.PostRepository;
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
public class PostDAOImpl implements PostDAO {
    private final PostRepository postRepository;

    /**
     * 게시물 저장
     * <p>
     * 게시물을 저장하는 메소드이다.
     *
     * @param postEntity
     * @return
     */
    @Override
    public PostEntity save(PostEntity postEntity) {
        if (postEntity.getTitle() == null || postEntity.getTitle().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("title은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postEntity.getContent() == null || postEntity.getContent().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("content는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postEntity.getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save postEntity: {}", postEntity);

        return postRepository.save(postEntity);
    }

    /**
     * 게시물 조회
     * <p>
     * 게시물을 조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public Optional<PostEntity> findById(Long postId) {
        if (postId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById postId : {}", postId);

        return postRepository.findById(postId);
    }

    /**
     * 게시물리스트 조회
     * <p>
     * 게시물리스트를 조회하는 메소드이다.
     *
     * @return
     */
    @Override
    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }

    /**
     * 조회수 업데이트
     * <p>
     * 조회수를 업데이트하는 메소드이다.
     *
     * @param postId
     */
    @Override
    public void updateViewCount(Long postId) {
        if (postId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("updateViewCount postId : {}", postId);

        postRepository.updateViewCount(postId);
    }
}
