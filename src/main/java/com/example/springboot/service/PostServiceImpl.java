package com.example.springboot.service;

import com.example.springboot.dao.PostDAO;
import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.PostEntity;
import com.example.springboot.repository.MemberRepository;
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

    @Override
    public PostResponseDTO save(PostRequestDTO postRequestDTO) {
        MemberEntity memberEntity = memberRepository.findById(postRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + postRequestDTO.getMemberId()));

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
                .resultCode("SUCCESS")
                .resultMessage("Post successfully saved.")
                .build();

    }
    @Override
    public PostResponseDTO findById(Long postId) {
        PostEntity postEntity = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

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
                .build();
    }
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
                        .resultCode("SUCCESS")
                        .resultMessage("Post retrieved.")
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    public void updateViewCount(Long postId) {
        postDAO.updateViewCount(postId);
    }
}
