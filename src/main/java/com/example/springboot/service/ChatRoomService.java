package com.example.springboot.service;

import com.example.springboot.dto.ChatRoomDTO;

import java.util.List;

public interface ChatRoomService {
    List<ChatRoomDTO> findAllChatRooms(Long memberId);
    void createChatRoom(Long memberId, ChatRoomDTO chatRoomDTO);
}
