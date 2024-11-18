package com.example.springboot.controller;

import com.example.springboot.dao.ChatroomDAO;
import com.example.springboot.dto.ChatRoomDTO;
import com.example.springboot.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;


    /**
     * 모든 채팅방 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity findAllChatRooms() {
        Long memberId = 1L;
        return ResponseEntity.ok(chatRoomService.findAllChatRooms(memberId));
    }

    /**
     * 채팅방 생성하기
     *
     * @param chatRoomDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        Long memberId = 1L;
        chatRoomService.createChatRoom(memberId, chatRoomDTO);
        return ResponseEntity.ok().build();
    }
}
