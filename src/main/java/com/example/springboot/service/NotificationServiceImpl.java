package com.example.springboot.service;

import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dao.NotificationDAO;
import com.example.springboot.dto.NotificationDTO;
import com.example.springboot.dto.NotificationMemberDTO;
import com.example.springboot.entity.domain.NotificationEntity;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.exception.FailGetNotificationException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final MemberDAO memberDAO;
    private final NotificationDAO notificationDAO;

    /**
     * 알람리스트 조회
     * <p>
     * 멤버아이디로 알람리스트를 조회하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public NotificationDTO findListNotification(String id) {
        // 알람리스트 조회
        List<NotificationMemberDTO> notificationMemberDTOList = memberDAO.findById(id)
                .map(memberEntity -> {
                    List<NotificationEntity> notificationEntityList = notificationDAO.findAllByMemberId(memberEntity.getMemberId());

                    if (notificationEntityList.isEmpty()) {
                        throw new FailGetNotificationException(ExceptionCodeEnum.NONEXISTENT_NOTIFICATION);
                    }

                    return notificationEntityList.stream()
                            .map(notificationEntity -> NotificationMemberDTO.builder()
                                    .notificationId(notificationEntity.getNotificationId())
                                    .message(notificationEntity.getMessage())
                                    .readed(notificationEntity.getReaded().toString().charAt(0)).build())
                            .toList();
                })
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        if (notificationMemberDTOList.isEmpty()) {
            throw new FailGetNotificationException(ExceptionCodeEnum.NONEXISTENT_NOTIFICATION);
        }

        return NotificationDTO.builder()
                .notificationMemberDTOList(notificationMemberDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
