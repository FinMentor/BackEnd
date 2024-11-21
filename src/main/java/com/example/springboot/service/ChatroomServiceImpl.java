package com.example.springboot.service;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.dao.ChatroomGroupDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.MessageDAO;
import com.example.springboot.dto.*;
import com.example.springboot.entity.domain.ChatroomEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetChatroomException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import com.example.springboot.vo.ChatroomGroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatroomServiceImpl implements ChatroomService {
    private final MemberDAO memberDAO;
    private final ChatroomDAO chatroomDAO;
    private final ChatroomGroupDAO chatroomGroupDAO;
    private final MessageDAO messageDAO;
    private final ChatService chatService;

    /**
     * 채팅방리스트 조회
     * <p>
     * 모든 채팅방을 조회하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public ChatroomDTO findAllChatroom(String id) {
        // 멤버 조회
        Long memberId = memberDAO.findById(id)
                .map(MemberEntity::getMemberId)
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 채팅방리스트 조회
        List<ChatroomDetailDTO> chatroomDetailDTOList = chatroomGroupDAO.getListChatroomByMemberId(memberId).stream()
                .map(chatroomGroupVO -> {
                    return ChatroomDetailDTO.builder()
                            .senderId((Long) chatroomGroupVO[0])
                            .senderNickName((String) chatroomGroupVO[1])
                            .receiverId((Long) chatroomGroupVO[2])
                            .receiverNickName((String) chatroomGroupVO[3])
                            .chatroomId((Long) chatroomGroupVO[4])
                            .receiverProfileUrl((String) chatroomGroupVO[5])
                            .recentMessage((String) chatroomGroupVO[6])
                            .createdAt(
                                    chatroomGroupVO[7] != null
                                            ? ((Timestamp) chatroomGroupVO[7]).toLocalDateTime()
                                            : null
                            )
                            .build();
                })
                .toList();

        if (chatroomDetailDTOList.isEmpty()) {
            throw new FailGetChatroomException(ExceptionCodeEnum.NONEXISTENT_CHATROOM);
        }

        return ChatroomDTO.builder()
                .chatroomDetailDTOList(chatroomDetailDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 채팅방 생성
     * <p>
     * 채팅방을 생성하는 메소드이다.
     *
     * @param id
     * @param chatroomRequestDTO
     * @return
     */
    @Override
    public ChatroomResponseDTO createChatroom(String id, ChatroomRequestDTO chatroomRequestDTO) {
        // 채팅방 생성
        ChatroomEntity chatroomEntity = chatroomDAO.createChatroom(ChatroomEntity.builder()
                .subject(chatroomRequestDTO.getSubject()).build());

        // 멤버 조회
        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        // 채팅방그룹 생성
        chatroomGroupDAO.createChatroomGroup(ChatroomGroupVO.builder()
                .memberId(memberEntity.getMemberId())
                .chatroomId(chatroomEntity.getChatroomId()).build());
        chatroomGroupDAO.createChatroomGroup(ChatroomGroupVO.builder()
                .memberId(chatroomRequestDTO.getMemberId())
                .chatroomId(chatroomEntity.getChatroomId()).build());

        // 회원가입시 AI챗봇 채팅방 생성
        if(chatroomRequestDTO.getSubject().equals("AI 챗봇")){
            chatService.saveMessage("AIBot", new MessageSendRequestDTO(chatroomEntity.getChatroomId(), "ㅎㅇ 난 챗봇",memberEntity.getNickname(), memberEntity.getProfileImageUrl()));

            chatroomGroupDAO.createChatroomGroup(ChatroomGroupVO.builder()
                    .memberId(100L)
                    .chatroomId(chatroomEntity.getChatroomId()).build());
        }

        return ChatroomResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 채팅방 삭제
     * <p>
     * 채팅방, 채팅내역을 삭제하는 메소드이다.
     *
     * @param id
     * @param chatroomId
     */
    @Override
    public ChatroomResponseDTO exitChatroom(String id, Long chatroomId) {
        // 채팅방 삭제
        chatroomDAO.deleteById(id, chatroomId);

        // 채팅방그룹 삭제
        chatroomGroupDAO.deleteByChatroomId(chatroomId);

        // 메시지 삭제
        messageDAO.deleteByChatroomId(chatroomId);

        return ChatroomResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
