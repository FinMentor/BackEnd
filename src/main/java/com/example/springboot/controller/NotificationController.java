package com.example.springboot.controller;

import com.example.springboot.dto.NotificationDTO;
import com.example.springboot.dto.NotificationRequestDTO;
import com.example.springboot.dto.NotificationResponseDTO;
import com.example.springboot.service.NotificationService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    /**
     * 알림리스트 조회
     * <p>
     * 멤버의 알림리스트를 조회하는 메소드이다.
     *
     * @param userDetails
     * @return
     */
    @GetMapping("/all")
    public ResponseResult<NotificationDTO> findListNotification(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("findListNotification userDetails : {}", userDetails);

        return ResponseResult.success(notificationService.findListNotification(userDetails.getUsername()));
    }

    /**
     * 알림 저장
     * <p>
     * 알림을 저장하는 메소드이다.
     *
     * @param notificationRequestDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseResult<NotificationResponseDTO> saveNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        log.info("saveNotification notificationRequestDTO : {}", notificationRequestDTO);

        return ResponseResult.success(notificationService.saveNotification(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), notificationRequestDTO));
    }
}
