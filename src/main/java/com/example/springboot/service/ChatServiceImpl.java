package com.example.springboot.service;

import com.example.springboot.dao.MessageDAO;
import com.example.springboot.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final MessageDAO messageDAO;

    @Override
    public void saveMessage(MessageDTO messageDTO) {
        messageDAO.saveMessage(messageDTO);
    }

    @Override
    public List<MessageDTO> getChatHistory(Long chatroomId, Long memberId, Pageable pageable) {
        // 페이징 처리된 메시지 목록 조회
        log.info("메시지 목록 조회 Service 시작");
        log.info("memberId: {}", memberId);
        return messageDAO.findByChatroomIdAndMemberId(chatroomId, memberId, pageable);
    }

}
