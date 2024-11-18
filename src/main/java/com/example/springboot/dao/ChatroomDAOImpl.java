package com.example.springboot.dao;

import com.example.springboot.entity.domain.ChatroomEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.ChatroomGroupRepository;
import com.example.springboot.repository.ChatroomRepository;
import com.example.springboot.repository.MessageRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatroomDAOImpl implements ChatroomDAO {
    private final ChatroomRepository chatroomRepository;
    private final ChatroomGroupRepository chatroomGroupRepository;
    private final MessageRepository messageRepository;

    /**
     * 채팅방 생성
     * <p>
     * 채팅방을 생성하는 메소드이다.
     *
     * @param chatroomEntity
     * @return
     */
    @Override
    public ChatroomEntity createChatroom(ChatroomEntity chatroomEntity) {
        if (chatroomEntity == null || chatroomEntity.getSubject() == null || chatroomEntity.getSubject().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("subject는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("createChatroom chatroomEntity : {}", chatroomEntity);

        return chatroomRepository.save(chatroomEntity);
    }

    /**
     * 채팅방 삭제
     * <p>
     * 채팅방, 채팅내역을 삭제하는 메소드이다.
     * @param id
     * @param chatroomId
     */
    @Override
    public void exitChatroom(String id, Long chatroomId) {
        chatroomRepository.deleteById(chatroomId);
        chatroomGroupRepository.deleteByChatroomId(chatroomId);
        messageRepository.deleteByChatroomId(chatroomId);

        log.info("exitChatroom chatroomId : {}", chatroomId);
    }
}
