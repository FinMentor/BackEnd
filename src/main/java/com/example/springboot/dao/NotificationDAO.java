package com.example.springboot.dao;

import com.example.springboot.entity.domain.NotificationEntity;

import java.util.List;

public interface NotificationDAO {
    List<NotificationEntity> findAllByMemberId(Long memberId);
}
