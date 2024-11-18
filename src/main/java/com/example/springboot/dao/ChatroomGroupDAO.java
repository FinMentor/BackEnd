package com.example.springboot.dao;

import com.example.springboot.vo.ChatroomGroupVO;

import java.util.List;

public interface ChatroomGroupDAO {
    List<ChatroomGroupVO> getListChatroomByMemberId(Long memberId);

    ChatroomGroupVO createChatroomGroup(ChatroomGroupVO chatroomGroupVO);
}
