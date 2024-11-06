package com.example.springboot.controller;

import com.example.springboot.dto.NotificationDTO;
import com.example.springboot.service.NotificationService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    // 특정 사용자의 알림 목록 불러오기 (DTO로 반환)
    @GetMapping
    public ResponseResult<List<NotificationDTO>> getNotificationsByMemberId(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("알림 목록 조회 요청 - memberId: {}", userDetails.getUsername());
        List<NotificationDTO> notifications = notificationService.getNotificationsByMemberId(userDetails.getUsername());
        return new ResponseResult<>(notifications, HttpStatus.OK);
    }

    // 특정 알림 삭제하기
    @DeleteMapping("/{notificationId}")
    public ResponseResult<String> deleteNotificationById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int notificationId) {
        log.info("알림 삭제 요청 - memberId: {}, notificationId: {}", userDetails.getUsername(), notificationId);
        notificationService.deleteNotificationById(notificationId, userDetails.getUsername());
        return new ResponseResult<>("알림이 삭제되었습니다.", HttpStatus.OK);
    }

    // 특정 사용자의 모든 알림 삭제하기
    @DeleteMapping("/all")
    public ResponseResult<String> deleteAllNotificationsByMemberId(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("알림 전체 삭제 요청 - memberId: {}", userDetails.getUsername());
        notificationService.deleteAllNotificationsByMemberId(userDetails.getUsername());
        return new ResponseResult<>("모든 알림이 삭제되었습니다.", HttpStatus.OK);
    }
}
