package com.example.springboot.service;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.dto.ChatRoomDTO;
import com.example.springboot.repository.ChatroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatroomDAO chatroomDAO;

    @Override
    public List<ChatRoomDTO> findChatRooms(Long memberId) {
        return chatroomDAO.getChatroomsByMemberId(memberId);
    }
}
