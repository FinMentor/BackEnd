package com.example.springboot.service;

import com.example.springboot.dto.*;

public interface PostService {
    PostResponseDTO save(PostRequestDTO postDTO); // 글 작성

    PostAllDTO postDetails(Long postId); // ID로 글 조회

    PostDTO findAll(Long mainCategoryId); // 글 목록 불러오기

    PostMentorDTO mentorPostAll(Long mainCategoryId);

    PostResponseDTO update(PostRequestDTO postRequestDTO);

    PostResponseDTO delete(Long postId);
}
