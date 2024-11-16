package com.example.springboot.dao;

import com.example.springboot.dto.ChatRoomDTO;
import com.example.springboot.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatroomDAOImpl implements ChatroomDAO {
    private final ChatroomRepository chatroomRepository;

    @Override
    public List<ChatRoomDTO> getChatroomsByMemberId(Long memberId) {
        return chatroomRepository.findByChatroomGroupVOList_MemberId(memberId)
                .stream()
                .map(entity -> ChatRoomDTO.builder()
                        .chatRoomId(entity.getChatroomId())
                        .subject(entity.getSubject())
                        .createdAt(entity.getCreatedAt())
                        .updatedAt(entity.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
