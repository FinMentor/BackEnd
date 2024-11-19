package com.example.springboot.dao;

import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.MessageRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MessageDAOImpl implements MessageDAO {
    private final MessageRepository messageRepository;

    /**
     * 메시지 저장
     * <p>
     * 전송한 메시지를 저장하는 메소드이다.
     *
     * @param messageEntity
     * @return
     */
    @Override
    public MessageEntity saveMessage(MessageEntity messageEntity) {
        if (messageEntity == null || messageEntity.getMemberEntity() == null || messageEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (messageEntity.getChatroomEntity() == null || messageEntity.getChatroomEntity().getChatroomId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("chatroomEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (messageEntity.getContent() == null || messageEntity.getContent().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("content는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("saveMessage messageEntity : {}", messageEntity);

        return messageRepository.save(messageEntity);
    }

    /**
     * 채팅내역 조회
     * <p>
     * 채팅방 접속시 채팅내역을 조회하는 메소드이다.
     *
     * @param chatroomId
     * @param memberId
     * @param pageable
     * @return
     */
    @Override
    public Page<MessageEntity> findByChatroomIdAndMemberId(Long chatroomId, Long memberId, Pageable pageable) {
        if (chatroomId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("chatroomId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (memberId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (pageable == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("pageable는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findByChatroomIdAndMemberId chatroomId : {}, memberId : {}, pageable : {}", chatroomId, memberId, pageable);

        return messageRepository.findByChatroomIdAndMemberId(chatroomId, memberId, pageable);
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
