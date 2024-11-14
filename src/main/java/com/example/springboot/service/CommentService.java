package com.example.springboot.service;

import com.example.springboot.dto.CommentRequestDTO;
import com.example.springboot.dto.CommentResponseDTO;

public interface CommentService {
    CommentResponseDTO save(CommentRequestDTO commentRequestDTO);
}
