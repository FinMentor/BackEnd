package com.example.springboot.service;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.dao.ChatroomGroupDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.MessageDAO;
import com.example.springboot.dto.ChatroomDTO;
import com.example.springboot.dto.ChatroomMemberDTO;
import com.example.springboot.dto.ChatroomRequestDTO;
import com.example.springboot.dto.ChatroomResponseDTO;
import com.example.springboot.entity.domain.ChatroomEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetChatroomException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import com.example.springboot.vo.ChatroomGroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<ChatroomMemberDTO> chatroomMemberDTOList = chatroomGroupDAO.getListChatroomByMemberId(memberId).stream()
                .map(chatroomGroupVO -> {
                    return ChatroomMemberDTO.builder()
                            .chatroomId(chatroomGroupVO.getChatroomEntity().getChatroomId())
                            .subject(chatroomGroupVO.getChatroomEntity().getSubject())
                            .createdAt(chatroomGroupVO.getChatroomEntity().getCreatedAt())
                            .updatedAt(chatroomGroupVO.getChatroomEntity().getUpdatedAt()).build();
                })
                .toList();

        if (chatroomMemberDTOList.isEmpty()) {
            throw new FailGetChatroomException(ExceptionCodeEnum.NONEXISTENT_CHATROOM);
        }

        return ChatroomDTO.builder()
                .chatroomMemberDTOList(chatroomMemberDTOList)
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
