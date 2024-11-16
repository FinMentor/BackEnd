package com.example.springboot.handler;

import com.example.springboot.dto.MessageDTO;
import com.example.springboot.entity.domain.MessageEntity;
import com.example.springboot.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private static final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        log.info("새로운 웹소켓 연결: {}", session.getId());
    }

//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        String payload = message.getPayload();
//        MessageDTO messageDTO = objectMapper.readValue(payload, MessageDTO.class);
//
//        // 메시지 저장
//        MessageEntity savedMessage = chatService.saveMessage(messageDTO);
//
//        // 모든 세션에 메시지 브로드캐스트
//        TextMessage broadcastMessage = new TextMessage(objectMapper.writeValueAsString(savedMessage));
//        for (WebSocketSession clientSession : sessions) {
//            clientSession.sendMessage(broadcastMessage);
//        }
//    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        log.info("웹소켓 연결 종료: {}", session.getId());
    }
} 