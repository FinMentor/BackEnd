package com.example.springboot.service;

import com.example.springboot.dto.NotificationDTO;
import com.example.springboot.dto.NotificationRequestDTO;
import com.example.springboot.dto.NotificationResponseDTO;

public interface NotificationService {
    NotificationDTO findListNotification(String id);

    NotificationResponseDTO saveNotification(String id, NotificationRequestDTO notificationRequestDTO);
}
