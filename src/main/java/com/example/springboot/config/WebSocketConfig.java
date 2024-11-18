package com.example.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");

        //     첫 번째 줄은 목적지가 "/app"으로 시작하는 메시지를 메시지 처리 메서드로 라우팅해야 한다고 정의합니다(이러한 메서드는 곧 정의할 것입니다).
        //     두 번째 줄은 목적지가 "/topic"으로 시작하는 메시지를 메시지 브로커로 라우팅해야 한다고 정의합니다.
        //     메시지 브로커는 특정 토픽에 구독된 모든 연결된 클라이언트에게 메시지를 브로드캐스트합니다.
    }
}