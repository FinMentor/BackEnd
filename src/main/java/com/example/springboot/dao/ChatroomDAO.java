package com.example.springboot.dao;

import com.example.springboot.dto.ChatRoomDTO;

import java.util.List;

public interface ChatroomDAO {
    List<ChatRoomDTO> getChatroomsByMemberId(Long memberId);
    void createChatroom(Long memberId, ChatRoomDTO chatroomDTO);
}
