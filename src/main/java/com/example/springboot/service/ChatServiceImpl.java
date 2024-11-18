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

    /**
     * 메시지 저장
     * <p>
     * 전송한 메시지를 저장하는 메소드이다.
     * @param messageDTO
     */
    @Override
    public void saveMessage(MessageDTO messageDTO) {
        messageDAO.saveMessage(messageDTO);
    }

    /**
     * 채팅내역 조회
     * <p>
     * 채팅방의 채팅 기록을 조회하는 메소드이다.
     * @param chatroomId
     * @param id
     * @param pageable
     * @return
     */
    @Override
    public List<MessageDTO> getChatHistory(Long chatroomId, String id, Pageable pageable) {
        return messageDAO.findByChatroomIdAndMemberId(chatroomId, id, pageable);
    }

}
