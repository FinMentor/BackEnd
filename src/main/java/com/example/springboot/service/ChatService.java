package com.example.springboot.service;

import com.example.springboot.dto.ChatRoomDTO;
import com.example.springboot.dto.MessageDTO;
import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatService {
    void saveMessage(MessageDTO messageDTO);
    List<MessageDTO> getChatHistory(Long chatroomId, Long memberId, Pageable pageable);
}
