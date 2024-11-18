package com.example.springboot.dao;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MessageRepository;
import com.example.springboot.util.ExceptionCodeEnum;
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

    /**
     * 메시지 저장
     * <p>
     * 전송한 메시지를 저장 메소드이다.
     * @param messageDTO
     */
    @Override
    public void saveMessage(MessageDTO messageDTO) {
        MessageEntity messageEntity = MessageEntity.builder()
                .memberId(messageDTO.getMemberId())
                .chatroomId(messageDTO.getChatroomId())
                .content(messageDTO.getContent())
                .messageType(messageDTO.getMessageType())
                .build();
        log.info("save message : {}", messageEntity);
        messageRepository.save(messageEntity);
    }

    /**
     * 채팅 내역 조회
     * <p>
     * 채팅방 접속시 채팅 내역을 조회하는 메소드이다.
     * @param chatroomId
     * @param id
     * @param pageable
     * @return
     */
    @Override
    public List<MessageDTO> findByChatroomIdAndMemberId(Long chatroomId, String id, Pageable pageable) {
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

    /**
     * 메시지 삭제
     * <p>
     * 메시지를 삭제하는 메소드이다.
     *
     * @param chatroomId
     */
    @Override
    public void deleteByChatroomId(Long chatroomId) {
        if (chatroomId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("chatroomId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("deleteByChatroomId chatroomId : {}", chatroomId);

        messageRepository.deleteByChatroomId(chatroomId);
    }
}
