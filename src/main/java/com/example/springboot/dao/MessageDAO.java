package com.example.springboot.dao;

import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageDAO {
    MessageEntity saveMessage(MessageEntity messageEntity);

    Page<MessageEntity> findByChatroomIdAndMemberId(Long chatroomId, Long memberId, Pageable pageable);

    void deleteByChatroomId(Long chatroomId);
}
