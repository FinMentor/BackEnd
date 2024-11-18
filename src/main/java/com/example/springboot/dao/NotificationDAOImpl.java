package com.example.springboot.dao;

import com.example.springboot.entity.domain.NotificationEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.NotificationRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NotificationDAOImpl implements NotificationDAO {
    private final NotificationRepository notificationRepository;

    /**
     * 알림전체 조회
     * <p>
     * 멤버아이디로 알림 전체를 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public List<NotificationEntity> findAllByMemberId(Long memberId) {
        if (memberId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findById memberId : {}", memberId);

        return notificationRepository.findAllByMemberId(memberId);
    }

    /**
     * 알림 저장
     * <p>
     * 알림을 저장하는 메소드이다.
     *
     * @param notificationEntity
     * @return
     */
    @Override
    public NotificationEntity save(NotificationEntity notificationEntity) {
        if (notificationEntity == null || notificationEntity.getMemberEntity() == null || notificationEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberEntity는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (notificationEntity.getMessage() == null || notificationEntity.getMessage().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("message는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save notificationEntity : {}", notificationEntity);

        return notificationRepository.save(notificationEntity);
    }
}
