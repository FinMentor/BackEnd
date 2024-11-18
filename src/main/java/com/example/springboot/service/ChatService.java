package com.example.springboot.service;

import com.example.springboot.dto.MessageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatService {
    void saveMessage(MessageDTO messageDTO);
    List<MessageDTO> getChatHistory(Long chatroomId, String id, Pageable pageable);
}
