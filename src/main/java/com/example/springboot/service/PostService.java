package com.example.springboot.service;

import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    PostResponseDTO save(PostRequestDTO postDTO); // 글 작성

    PostResponseDTO findById(Long postId); // ID로 글 조회

    List<PostResponseDTO> findAll(); // 글 목록 불러오기

    PostResponseDTO update(PostRequestDTO postRequestDTO);

    void delete(Long postId);
}
