package com.example.springboot.dao;

import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MessageDAO {
    MessageEntity saveMessage(MessageEntity messageEntity);
    Page<MessageEntity> findByChatroomId(Long chatroomId, Pageable pageable);

    void deleteByChatroomId(Long chatroomId);
}
