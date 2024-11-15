package com.example.springboot.service;

import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;

import java.util.List;

public interface CommentService {
    CommentResponseDTO save(CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> findAll(Long postId);

    CommentResponseDTO update(CommentRequestDTO commentRequestDTO);

    CommentResponseDTO delete(Long commentId);
}
