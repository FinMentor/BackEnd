package com.example.springboot.controller;

import com.example.springboot.dto.ChatroomDTO;
import com.example.springboot.dto.ChatroomRequestDTO;
import com.example.springboot.dto.ChatroomResponseDTO;
import com.example.springboot.service.ChatroomService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chatroom")
@RequiredArgsConstructor
@Slf4j
public class ChatroomController {
    private final ChatroomService chatroomService;

    /**
     * 채팅방리스트 조회
     * <p>
     * 모든 채팅방을 조회하는 메소드이다.
     *
     * @param userDetails
     * @return
     */
    @GetMapping
    public ResponseResult<ChatroomDTO> findAllChatroom(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("findAllChatroom userDetails : {}", userDetails);

        return ResponseResult.success(chatroomService.findAllChatroom(userDetails.getUsername()));
    }

    /**
     * 채팅방 생성
     * <p>
     * 채팅방을 생성하는 메소드이다.
     *
     * @param chatroomRequestDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseResult<ChatroomResponseDTO> createChatroom(@RequestBody ChatroomRequestDTO chatroomRequestDTO) {
        log.info("createChatroom chatroomRequestDTO : {}", chatroomRequestDTO);

        return ResponseResult.success(chatroomService.createChatroom(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), chatroomRequestDTO));
    }
}