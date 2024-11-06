package com.example.springboot.service;

import com.example.springboot.dto.NotificationDTO;
import com.example.springboot.entity.NotificationEntity;
import com.example.springboot.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationDTO> getNotificationsByMemberId(String memberId) {
        List<NotificationEntity> notifications = notificationRepository.getNotificationsByMemberId(memberId);
        if (log.isInfoEnabled()) {
            log.info("hi : {}", notifications.get(0).getMessageType());
        }
        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotificationById(int notificationId, String memberId) {
        notificationRepository.deleteNotificationById(notificationId, memberId);
    }

    @Override
    public void deleteAllNotificationsByMemberId(String memberId) {
        notificationRepository.deleteAllNotificationsByMemberId(memberId);
    }

    // VO를 DTO로 변환하는 메서드
    private NotificationDTO convertToDTO(NotificationEntity notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setMemberId(notification.getMemberId());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setMessageType(notification.getMessageType());
        notificationDTO.setReaded(notification.getReaded());
        notificationDTO.setCreatedAt(notification.getCreatedAt());
        notificationDTO.setUpdatedAt(notification.getUpdatedAt());
        return notificationDTO;
    }
}
