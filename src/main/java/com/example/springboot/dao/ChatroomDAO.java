package com.example.springboot.dao;

import com.example.springboot.entity.domain.ChatroomEntity;

import java.util.Optional;

public interface ChatroomDAO {
    ChatroomEntity createChatroom(ChatroomEntity chatroomEntity);

    void deleteById(String id, Long chatroomId);

    Optional<ChatroomEntity> findById(Long chatroomId);
}
