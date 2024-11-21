package com.example.springboot.service;

import com.example.springboot.dto.PostDTO;
import com.example.springboot.dto.PostMentorDTO;
import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    PostResponseDTO save(PostRequestDTO postDTO); // 글 작성

    PostResponseDTO findById(Long postId); // ID로 글 조회

    PostDTO findAll(Long mainCategoryId); // 글 목록 불러오기

    PostMentorDTO mentorPostAll(Long mainCategoryId);

    PostResponseDTO update(PostRequestDTO postRequestDTO);

    PostResponseDTO delete(Long postId);
}
