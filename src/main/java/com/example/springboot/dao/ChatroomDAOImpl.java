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

import java.util.Optional;

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
     * 채팅방을 삭제하는 메소드이다.
     *
     * @param id
     * @param chatroomId
     */
    @Override
    public void deleteById(String id, Long chatroomId) {
        if (id == null || id.isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("id는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("exitChatroom id : {}, chatroomId : {}", id, chatroomId);

        chatroomRepository.deleteById(chatroomId);
    }

    /**
     * 채팅방 조회
     * <p>
     * 채팅방아이디로 채팅방을 조회하는 메소드이다.
     *
     * @param chatroomId
     * @return
     */
    @Override
    public Optional<ChatroomEntity> findById(Long chatroomId) {
        if (chatroomId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("chatroomId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById chatroomId : {}", chatroomId);

        return chatroomRepository.findById(chatroomId);
    }
}
