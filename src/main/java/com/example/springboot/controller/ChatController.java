package com.example.springboot.controller;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.service.ChatService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 채팅 내역 조회
     * <p>
     * 채팅방 접속시 채팅 내역을 조회하는 메소드이다.
     * @param chatroomId
     * @param page
     * @param size
     * @param userDetails
     * @return
     */
    @GetMapping("/api/v1/chat/rooms/{chatroomId}/enter")
    public ResponseResult<Map<String, Object>> enterChatRoom(
            @PathVariable Long chatroomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @AuthenticationPrincipal UserDetails userDetails) {

        log.info("채팅방 입장 요청 - chatroomId: {}, page: {}, size: {}", chatroomId, page, size);
        Pageable pageable = PageRequest.of(page, size);
        List<MessageDTO> messages = chatService.getChatHistory(chatroomId, userDetails.getUsername(), pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("messages", messages);

        return ResponseResult.success(response);
    }

    /**
     * 메시지 전송 및 저장
     * <p>
     * 웹소켓을 통한 메시지를 전송 및 저장하는 메소드이다.
     * @param messageDTO
     * @param chatRoomId
     */
    @MessageMapping("/{chatRoomId}/chat.sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO, @DestinationVariable String chatRoomId) {
        chatService.saveMessage(messageDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, messageDTO);
    }
}
