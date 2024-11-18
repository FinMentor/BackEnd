package com.example.springboot.dao;

import com.example.springboot.dto.MessageDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageDAO {
    void saveMessage(MessageDTO messageDTO);

    List<MessageDTO> findByChatroomIdAndMemberId(Long chatroomId, String id, Pageable pageable);

    void deleteByChatroomId(Long chatroomId);
}
