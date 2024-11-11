package com.example.springboot.service;

import com.example.springboot.dto.PostDTO;

public interface PostService {

    PostDTO save(PostDTO postDTO); // 글 작성
    PostDTO getPostById(Long postId); // ID로 글 조회

}
