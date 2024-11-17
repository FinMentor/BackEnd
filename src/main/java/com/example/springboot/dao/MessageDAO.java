package com.example.springboot.dao;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageDAO {
    void saveMessage(MessageDTO messageDTO);
    List<MessageDTO> findByChatroomIdAndMemberId(Long chatroomId, Long memberId, Pageable pageable);
}
