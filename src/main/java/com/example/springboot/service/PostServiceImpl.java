package com.example.springboot.service;

import com.example.springboot.dao.MainCategoryDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.PostDAO;
import com.example.springboot.dto.PostDTO;
import com.example.springboot.dto.PostDetailsDTO;
import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.exception.FailGetMainCategoryException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.exception.PostNotFoundException;
import com.example.springboot.util.CommonCodeEnum;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostDAO postDAO;
    private final MemberDAO memberDAO;
    private final MainCategoryDAO mainCategoryDAO;

    /**
     * 게시글 저장
     * <p>
     * 커뮤니티 게시물을 저장하는 메소드이다.
     *
     * @param postRequestDTO
     * @return
     */
    @Override
    public PostResponseDTO save(PostRequestDTO postRequestDTO) {
        log.info("save postRequestDTO : {}", postRequestDTO);

        // 멤버 조회
        MemberEntity memberEntity = memberDAO.findById(postRequestDTO.getId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 메인카테고리 조회
        MainCategoryEntity mainCategoryEntity = mainCategoryDAO.findById(postRequestDTO.getMainCategoryId())
                .orElseThrow(() -> new FailGetMainCategoryException(ExceptionCodeEnum.NONEXISTENT_CATEGORY));

        // 게시물 저장
        PostEntity postEntity = postDAO.save(PostEntity.builder()
                .memberEntity(memberEntity)
                .mainCategoryEntity(mainCategoryEntity)
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent()).build());

        return PostResponseDTO.builder()
                .postId(postEntity.getPostId())
                .memberId(postEntity.getMemberEntity().getMemberId())
                .mainCategoryId(postEntity.getMainCategoryEntity().getMainCategoryId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .viewCount(0L)
                .likeCount(0L)
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    /**
     * 게시글 조회
     * <p>
     * 게시아이디로 게시글을 조회하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public PostResponseDTO findById(Long postId) {
        log.info("findById postId : {}", postId);

        postDAO.updateViewCount(postId);

        return postDAO.findById(postId).map(
                        postEntity -> {
                            return PostResponseDTO.builder()
                                    .postId(postEntity.getPostId())
                                    .memberId(postEntity.getMemberId())
                                    .mainCategoryId(postEntity.getMainCategoryId())
                                    .title(postEntity.getTitle())
                                    .content(postEntity.getContent())
                                    .viewCount(postEntity.getViewCount())
                                    .likeCount(postEntity.getLikeCount())
                                    .createdAt(postEntity.getCreatedAt())
                                    .updatedAt(postEntity.getUpdatedAt())
                                    .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                    .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                                    .build();
                        })
                .orElseThrow(() -> new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST));
    }

    /**
     * 게시글리스트 조회
     * <p>
     * 게시글리스트를 조회하는 메소드이다.
     *
     * @param mainCategoryId
     * @return
     */
    @Override
    public PostDTO findAll(Long mainCategoryId) {
        List<PostDetailsDTO> postDetailsDTOList;

        if (mainCategoryId == null) {
            // 전체 게시물 조회
            postDetailsDTOList = postDAO.findAll(CommonCodeEnum.NORMAL.getValue()).stream().map(
                            postDetailsDTO -> PostDetailsDTO.builder()
                                    .postId((Long) postDetailsDTO[0])
                                    .title((String) postDetailsDTO[1])
                                    .content((String) postDetailsDTO[2])
                                    .likeCount((Long) postDetailsDTO[3])
                                    .commentCount((Long) postDetailsDTO[4])
                                    .createdAt((String) postDetailsDTO[5]).build())
                    .toList();
        } else {
            // 카테고리별 게시물 조회
            postDetailsDTOList = postDAO.findAllByMainCategoryId(mainCategoryId, CommonCodeEnum.NORMAL.getValue()).stream().map(
                            postDetailsDTO -> PostDetailsDTO.builder()
                                    .postId((Long) postDetailsDTO[0])
                                    .title((String) postDetailsDTO[1])
                                    .content((String) postDetailsDTO[2])
                                    .likeCount((Long) postDetailsDTO[3])
                                    .commentCount((Long) postDetailsDTO[4])
                                    .createdAt((String) postDetailsDTO[5]).build())
                    .toList();
        }

        if (postDetailsDTOList.isEmpty()) {
            throw new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST);
        }

        return PostDTO.builder()
                .postDetailsDTOList(postDetailsDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 게시글 수정
     * <p>
     * 게시글을 수정하는 메서드이다.
     *
     * @param postRequestDTO
     * @return
     */
    @Override
    public PostResponseDTO update(PostRequestDTO postRequestDTO) {
        log.info("update postRequestDTO : {}", postRequestDTO);

        // 게시글 조회
        PostEntity existingPost = postDAO.findById(postRequestDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST));

        // 멤버 조회
        MemberEntity memberEntity = memberDAO.findById(postRequestDTO.getId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 메인카테고리 조회
        MainCategoryEntity mainCategoryEntity = mainCategoryDAO.findById(postRequestDTO.getMainCategoryId())
                .orElseThrow(() -> new FailGetMainCategoryException(ExceptionCodeEnum.NONEXISTENT_CATEGORY));

        // 게시글 수정
        PostEntity updatedPost = postDAO.save(PostEntity.builder()
                .postId(existingPost.getPostId())
                .memberEntity(memberEntity)
                .mainCategoryEntity(mainCategoryEntity)
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .viewCount(existingPost.getViewCount()) // 기존 조회수 유지
                .likeCount(existingPost.getLikeCount()) // 기존 좋아요수 유지
                .build());

        return PostResponseDTO.builder()
                .postId(updatedPost.getPostId())
                .memberId(updatedPost.getMemberEntity().getMemberId())
                .mainCategoryId(updatedPost.getMainCategoryEntity().getMainCategoryId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .viewCount(updatedPost.getViewCount())
                .likeCount(updatedPost.getLikeCount())
                .createdAt(updatedPost.getCreatedAt())
                .updatedAt(updatedPost.getUpdatedAt())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    /**
     * 게시글 삭제
     * <p>
     * 게시아이디로 게시글을 삭제하는 메소드이다.
     *
     * @param postId
     * @return
     */
    @Override
    public PostResponseDTO delete(Long postId) {
        // 게시글 조회
        postDAO.findById(postId).orElseThrow(() -> new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST));

        // 게[시글 삭제
        postDAO.deleteById(postId);

        return PostResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
