package com.example.springboot.controller;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    // 채팅방의 이전 메시지 조회
//    @GetMapping("/api/v1/chat/rooms/{chatroomId}/messages")
//    public ResponseEntity<List<MessageDTO>> getChatHistory(
//            @PathVariable Long chatroomId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "50") int size,
//            @RequestParam Long memberId) {
//        Pageable pageable = PageRequest.of(page, size);
//        List<MessageDTO> chatHistory = chatService.getChatHistory(chatroomId, memberId, pageable);
//        log.info(chatHistory.toString());
//        return ResponseEntity.ok(chatHistory);
//    }

    // 채팅방 입장 시 필요한 초기 데이터 조회 (메시지 히스토리 + 채팅방 정보)
    @GetMapping("/api/v1/chat/rooms/{chatroomId}/enter")
    public ResponseEntity<Map<String, Object>> enterChatRoom(
            @PathVariable Long chatroomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam Long memberId) {
        
            log.info("채팅방 입장 요청 - chatroomId: {}, page: {}, size: {}", chatroomId, page, size);
            Pageable pageable = PageRequest.of(page, size);
            Map<String, Object> response = new HashMap<>();
            List<MessageDTO> messages = chatService.getChatHistory(chatroomId, memberId, pageable);
                
            log.info("조회된 메시지 수: {}", messages.size());
                response.put("messages", messages);
            
                return ResponseEntity.ok(response);
    }

    // 기존 WebSocket 메시지 처리
    @MessageMapping("/{chatRoomId}/chat.sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO, @DestinationVariable String chatRoomId) {
        chatService.saveMessage(messageDTO);
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, messageDTO);
    }
}
