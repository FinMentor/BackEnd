package com.example.springboot.controller;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatroomDAO chatroomDAO;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity findChatRooms() {
        Long memberId = 1L;
        return ResponseEntity.ok(chatroomDAO.getChatroomsByMemberId(memberId));
    }
}
