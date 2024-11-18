package com.example.springboot.dao;

import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.ChatroomGroupRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.vo.ChatroomGroupVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatroomGroupDAOImpl implements ChatroomGroupDAO {
    private final ChatroomGroupRepository chatroomGroupRepository;

    /**
     * 채팅방리스트 조회
     * <p>
     * 멤버아이디로 채팅방리스트를 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public List<ChatroomGroupVO> getListChatroomByMemberId(Long memberId) {
        if (memberId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("getListChatroomByMemberId memberId : {}", memberId);

        return chatroomGroupRepository.getListChatroomByMemberId(memberId);
    }

    /**
     * 채팅방그룹 생성
     * <p>
     * 채팅방그룹을 생성하는 메소드이다.
     *
     * @param chatroomGroupVO
     * @return
     */
    @Override
    public ChatroomGroupVO createChatroomGroup(ChatroomGroupVO chatroomGroupVO) {
        if (chatroomGroupVO == null || chatroomGroupVO.getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (chatroomGroupVO.getChatroomId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("chatroomId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("createChatroomGroup chatroomGroupVO : {}", chatroomGroupVO);

        return chatroomGroupRepository.save(chatroomGroupVO);
    }
}
