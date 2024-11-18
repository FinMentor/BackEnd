package com.example.springboot.dao;

import com.example.springboot.entity.domain.ChatroomEntity;

public interface ChatroomDAO {
    ChatroomEntity createChatroom(ChatroomEntity chatroomEntity);

    void exitChatroom(String id, Long chatroomId);
}
