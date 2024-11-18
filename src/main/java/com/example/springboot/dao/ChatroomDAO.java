package com.example.springboot.dao;

import com.example.springboot.entity.domain.ChatroomEntity;

public interface ChatroomDAO {
    ChatroomEntity createChatroom(ChatroomEntity chatroomEntity);

    void deleteById(String id, Long chatroomId);
}
