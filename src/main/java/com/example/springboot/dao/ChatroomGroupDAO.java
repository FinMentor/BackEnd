package com.example.springboot.dao;

import com.example.springboot.dto.ChatroomDetailDTO;
import com.example.springboot.vo.ChatroomGroupVO;

import java.util.List;

public interface ChatroomGroupDAO {
    List<Object[]> getListChatroomByMemberId(Long memberId);

    ChatroomGroupVO createChatroomGroup(ChatroomGroupVO chatroomGroupVO);

    void deleteByChatroomId(Long chatroomId);
}
