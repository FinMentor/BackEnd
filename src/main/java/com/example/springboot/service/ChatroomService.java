package com.example.springboot.service;

import com.example.springboot.dto.ChatroomDTO;
import com.example.springboot.dto.ChatroomRequestDTO;
import com.example.springboot.dto.ChatroomResponseDTO;

public interface ChatroomService {
    ChatroomDTO findAllChatroom(String id);

    ChatroomResponseDTO createChatroom(String id, ChatroomRequestDTO chatroomRequestDTO);

    ChatroomResponseDTO exitChatroom(String id, Long chatroomId);
}
