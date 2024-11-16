package com.example.springboot.dao;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageDAOImpl implements MessageDAO {
    private final MessageRepository messageRepository;

    @Override
    public void saveMessage(MessageEntity messageEntity) {
        messageRepository.save(messageEntity);
    }

    @Override
    public List<MessageDTO> findByChatroomIdAndMemberId(Long chatroomId, Long memberId, Pageable pageable) {
        log.info("메시지 DAO시작");
        List<MessageDTO> messageDTOList = messageRepository.findByChatroomId(chatroomId, pageable)
                .getContent()
                .stream()
                .map(entity -> MessageDTO.builder()
                        .messageId(entity.getMessageId())
                        .memberId(entity.getMemberId())
                        .chatroomId(entity.getChatroomId())
                        .content(entity.getContent())
                        .createdAt(entity.getCreatedAt())
                        .updatedAt(entity.getUpdatedAt())
                        .messageType(entity.getMessageType())
                        .build())
                .collect(Collectors.toList());
        log.info("메시지 조회 완료 :{}", messageDTOList);
        return messageDTOList;
    }
}
