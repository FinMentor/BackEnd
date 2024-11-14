package com.example.springboot.service;

import com.example.springboot.dao.PostDAO;
import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.exception.PostNotFoundException;
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
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;
    private final MemberRepository memberRepository;

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
        MemberEntity memberEntity = memberRepository.findById(postRequestDTO.getMemberId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // PostEntity 빌드 시 MemberEntity 설정
        PostEntity postEntity = PostEntity.builder()
                .memberEntity(memberEntity) // MemberEntity 설정
                .mainCategoryId(postRequestDTO.getMainCategoryId())
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .build();

        PostEntity savedPost = postDAO.save(postEntity);

        return PostResponseDTO.builder()
                .postId(savedPost.getPostId())
                .memberId(savedPost.getMemberId())
                .mainCategoryId(savedPost.getMainCategoryId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .viewCount(savedPost.getViewCount().longValue())
                .likeCount(savedPost.getLikeCount().longValue())
                .createdAt(savedPost.getCreatedAt())
                .updatedAt(LocalDateTime.now())
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
    /**
     * 게시글 업데이트
     * <p>
     * 게시글 업데이트 하는 메서드이다.
     *
     * @return
     */
    @Override
    public PostResponseDTO update(PostRequestDTO postRequestDTO) {
        // 게시글 조회 및 예외 처리
        PostEntity existingPost = postDAO.findById(postRequestDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST));

        // MemberEntity 조회 및 예외 처리
        MemberEntity memberEntity = memberRepository.findById(postRequestDTO.getMemberId())
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 기존 게시글의 필드 업데이트
        existingPost = PostEntity.builder()
                .postId(existingPost.getPostId())
                .memberEntity(memberEntity) // MemberEntity 설정
                .mainCategoryId(postRequestDTO.getMainCategoryId())
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .viewCount(existingPost.getViewCount()) // 기존 조회수 유지
                .likeCount(existingPost.getLikeCount()) // 기존 좋아요 수 유지
                .build();

        // 업데이트된 게시글 저장
        PostEntity updatedPost = postDAO.save(existingPost);

        // 결과 반환
        return PostResponseDTO.builder()
                .postId(updatedPost.getPostId())
                .memberId(updatedPost.getMemberId())
                .mainCategoryId(updatedPost.getMainCategoryId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .viewCount(updatedPost.getViewCount().longValue())
                .likeCount(updatedPost.getLikeCount().longValue())
                .createdAt(updatedPost.getCreatedAt())
                .updatedAt(updatedPost.getUpdatedAt())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }
    @Override
    public void delete(Long postId) {
        postDAO.findById(postId).orElseThrow(() -> new PostNotFoundException(ExceptionCodeEnum.NONEXISTENT_POST));
        postDAO.deleteById(postId);
    }
}
