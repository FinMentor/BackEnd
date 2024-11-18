package com.example.springboot.service;

import com.example.springboot.dto.NotificationDTO;

public interface NotificationService {
    NotificationDTO findListNotification(String id);
}
