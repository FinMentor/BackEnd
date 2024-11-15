package com.example.springboot.service;

import com.example.springboot.dao.MainCategoryDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.PostDAO;
import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.domain.MainCategoryEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.exception.FailGetMainCategoryException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.exception.PostNotFoundException;
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
     * 게시글아이디로 게시글을 조회하는 메소드이다.
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
                                    .viewCount(postEntity.getViewCount().longValue())
                                    .likeCount(postEntity.getLikeCount().longValue())
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
     * @return
     */
    @Override
    public List<PostResponseDTO> findAll() {
        List<PostEntity> posts = postDAO.findAll();

        return posts.stream()
                .map(postEntity -> PostResponseDTO.builder()
                        .postId(postEntity.getPostId())
                        .memberId(postEntity.getMemberId())
                        .mainCategoryId(postEntity.getMainCategoryId())
                        .title(postEntity.getTitle())
                        .content(postEntity.getContent())
                        .viewCount(postEntity.getViewCount().longValue())
                        .likeCount(postEntity.getLikeCount().longValue())
                        .createdAt(postEntity.getCreatedAt())
                        .updatedAt(postEntity.getUpdatedAt())
                        .resultCode(ResultCodeEnum.SUCCESS.getValue())
                        .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
