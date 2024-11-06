package com.example.springboot.repository;

import com.example.springboot.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
    List<NotificationEntity> getNotificationsByMemberId(@Param("memberId") String memberId);

    int deleteNotificationById(@Param("notificationId") int notificationId, @Param("memberId") String memberId);

    int deleteAllNotificationsByMemberId(@Param("memberId") String memberId);
}
