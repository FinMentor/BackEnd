package com.example.springboot.service;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.dto.MessageSendRequestDTO;
import com.example.springboot.dto.MessageSendResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ChatService {
    MessageSendResponseDTO saveMessage(String id, MessageSendRequestDTO messageSendRequestDTO);

    MessageDTO getChatHistory(Long chatroomId, String id, Pageable pageable);
}
