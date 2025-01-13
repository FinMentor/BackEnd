package com.example.springboot.controller;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.dto.MessageSendRequestDTO;
import com.example.springboot.dto.MessageSendResponseDTO;
import com.example.springboot.service.ChatService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * 채팅내역 조회
     * <p>
     * 채팅방 접속시 채팅내역을 조회하는 메소드이다.
     *
     * @param chatroomId
     * @param page
     * @param size
     * @param userDetails
     * @return
     */
    @GetMapping("/rooms/enter")
    public ResponseResult<MessageDTO> enterChatroom(@RequestParam Long chatroomId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @AuthenticationPrincipal UserDetails userDetails) {
        log.info("enterChatroom chatroomId : {}, page : {}, size : {}, userDetails : {}", chatroomId, page, size, userDetails);

        return ResponseResult.success(chatService.getChatHistory(chatroomId, userDetails.getUsername(), PageRequest.of(page, size)));
    }

    /**
     * 메시지 저장 및 전송
     * <p>
     * 웹소켓을 통해 메시지를 저장하고 전송하는 메소드이다.
     *
     * @param messageSendRequestDTO
     * @return
     */
    @MessageMapping("/send")
    public ResponseResult<MessageSendResponseDTO> sendMessage(@Payload MessageSendRequestDTO messageSendRequestDTO,
                                                              Principal principal) {
        log.info("roomId:{}",messageSendRequestDTO.getChatroomId());
        messagingTemplate.convertAndSend("/topic/chat/" + messageSendRequestDTO.getChatroomId(), messageSendRequestDTO);

        log.info("sendMessage messageDetailsDTO : {}", messageSendRequestDTO);
        return ResponseResult.success(chatService.saveMessage(principal.getName(), messageSendRequestDTO));
    }
}
