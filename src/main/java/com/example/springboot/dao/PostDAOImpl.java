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
        validateRequiredFields(postEntity);

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
     * 게시글리스트 조회
     * <p>
     * 전체 게시글리스트를 조회하는 메소드이다.
     *
     * @param postType
     * @return
     */
    @Override
    public List<Object[]> findAll(String postType) {
        if (postType == null || postType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAll postType : {}", postType);

        return postRepository.findAll(postType);
    }

    /**
     * 게시글리스트 조회
     * <p>
     * 카테고리별 게시글리스트를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @param postType
     * @return
     */
    @Override
    public List<Object[]> findAllByMainCategoryId(Long mainCategoryId, String postType) {
        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postType == null || postType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAll mainCategoryId : {}, postType : {}", mainCategoryId, postType);

        return postRepository.findAllByMainCategoryId(mainCategoryId, postType);
    }

    /**
     * 고수 게시물리스트 전체 조회
     * <p>
     * 고수의 게시물리스트를 전체 조회하는 메소드이다.
     *
     * @param postType
     * @return
     */
    @Override
    public List<PostEntity> findAllMentor(String postType) {
        if (postType == null || postType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAllMentor postType : {}", postType);

        return postRepository.findAllMentor(postType);
    }

    /**
     * 고수 게시물리스트 전체 조회 By categoryId
     * <p>
     * 카테고리별로 고수 게시물리스트 전체를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @param postType
     * @return
     */
    @Override
    public List<PostEntity> findAllMentorByMainCategoryId(Long mainCategoryId, String postType) {
        if (mainCategoryId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postType == null || postType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findAllMentorByMainCategoryId mainCategoryId : {}, postType : {}", mainCategoryId, postType);

        return postRepository.findAllMentorByMainCategoryId(mainCategoryId, postType);
    }

    /**
     * 조회수 수정
     * <p>
     * 조회수를 수정하는 메소드이다.
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

    /**
     * 게시물 수정
     * <p>
     * 게시물을 수정하는 메소드이다
     *
     * @param postEntity
     * @return
     */
    @Override
    public PostEntity update(PostEntity postEntity) {
        validateRequiredFields(postEntity);

        log.info("update postEntity: {}", postEntity);

        return postRepository.save(postEntity);
    }

    /**
     * 필수값 검증
     * <p>
     * 필수값을 검증하는 메소드이다.
     *
     * @param postEntity
     */
    private void validateRequiredFields(PostEntity postEntity) {
        if (postEntity.getMemberEntity() == null || postEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postEntity.getMainCategoryEntity() == null || postEntity.getMainCategoryEntity().getMainCategoryId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("mainCategoryEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postEntity.getTitle() == null || postEntity.getTitle().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("title은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postEntity.getContent() == null || postEntity.getContent().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("content는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }
    }

    /**
     * 게시글 삭제
     * <p>
     * 게시아이디로 게시글을 삭제하는 메소드이다.
     *
     * @param postId
     */
    @Override
    public void deleteById(Long postId) {
        if (postId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("deleteById postId : {}", postId);

        postRepository.deleteById(postId);
    }

    /**
     * 게시글상세 조회
     * <p>
     * 게시글아이디로 게시글을 상세조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public List<Object[]> postDetails(Long postId, String postType) {
        if (postId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (postType == null || postType.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("postType은 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("postDetails postId : {}, postType : {}", postId, postType);

        return postRepository.postDetails(postId, postType);
    }
}
