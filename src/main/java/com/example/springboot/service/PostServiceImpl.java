package com.example.springboot.service;

import com.example.springboot.dao.PostDAO;
import com.example.springboot.dto.PostDTO;
import com.example.springboot.entity.domain.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public PostDTO save(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity().builder()
                .postId(postDTO.getPostId())
                .memberId(postDTO.getMemberId())
                .mainCategoryId(postDTO.getMainCategoryId())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .viewCount(0)
                .likeCount(0)
                .build();

        PostEntity savedPost = postDAO.save(postEntity);

        postDTO.setPostId(savedPost.getPostId());
        postDTO.setCreatedAt(LocalDateTime.now());
        return postDTO;
    }
    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postDAO.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return PostDTO.builder()
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
}
