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
     * 알람전체 조회
     * <p>
     * 멤버아이디로 알람리스트를 전체조회하는 메소드이다.
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
}
