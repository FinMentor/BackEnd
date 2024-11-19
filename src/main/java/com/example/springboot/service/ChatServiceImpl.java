package com.example.springboot.service;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.MessageDAO;
import com.example.springboot.dto.MessageDTO;
import com.example.springboot.dto.MessageDetailsDTO;
import com.example.springboot.dto.MessageSendRequestDTO;
import com.example.springboot.dto.MessageSendResponseDTO;
import com.example.springboot.entity.domain.ChatroomEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.exception.FailGetChatroomException;
import com.example.springboot.exception.FailGetMainCategoryException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final MemberDAO memberDAO;
    private final MessageDAO messageDAO;
    private final ChatroomDAO chatroomDAO;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 메시지 저장
     * <p>
     * 전송한 메시지를 저장하는 메소드이다.
     *
     * @param id
     * @param messageSendRequestDTO
     * @return
     */
    @Override
    public MessageSendResponseDTO saveMessage(String id, MessageSendRequestDTO messageSendRequestDTO) {
        // 멤버 조회
        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 채팅방 조회
        ChatroomEntity chatroomEntity = chatroomDAO.findById(messageSendRequestDTO.getChatroomId()).orElseThrow(() -> new FailGetChatroomException(ExceptionCodeEnum.NONEXISTENT_CHATROOM));

        // 메시지 저장
        messageDAO.saveMessage(MessageEntity.builder()
                .memberEntity(memberEntity)
                .chatroomEntity(chatroomEntity)
                .content(messageSendRequestDTO.getContent()).build());

        messagingTemplate.convertAndSend("/topic/chatroom" + messageSendRequestDTO.getChatroomId(), messageSendRequestDTO);

        return MessageSendResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 채팅내역 조회
     * <p>
     * 채팅방의 채팅기록을 조회하는 메소드이다.
     *
     * @param chatroomId
     * @param id
     * @param pageable
     * @return
     */
    @Override
    public MessageDTO getChatHistory(Long chatroomId, String id, Pageable pageable) {
        // 멤버 조회
        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMainCategoryException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 채팅내역 조회
        List<MessageDetailsDTO> messageDetailsDTOList = messageDAO.findByChatroomIdAndMemberId(chatroomId, memberEntity.getMemberId(), pageable).getContent().stream().map(
                        messageEntity ->
                                MessageDetailsDTO.builder()
                                        .messageId(messageEntity.getMessageId())
                                        .memberId(messageEntity.getMemberId())
                                        .chatroomId(messageEntity.getChatroomId())
                                        .content(messageEntity.getContent())
                                        .messageType(messageEntity.getMessageType().toString().charAt(0))
                                        .createdAt(messageEntity.getCreatedAt())
                                        .updatedAt(messageEntity.getUpdatedAt()).build()
                )
                .toList();

        return MessageDTO.builder()
                .messageDetailsDTOList(messageDetailsDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
