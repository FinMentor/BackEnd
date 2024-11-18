package com.example.springboot.repository;

import com.example.springboot.vo.ChatroomGroupVO;
import com.example.springboot.vo.id.ChatroomGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomGroupRepository extends JpaRepository<ChatroomGroupVO, ChatroomGroupId> {
    @Query("SELECT cg FROM ChatroomGroupVO cg JOIN FETCH cg.chatroomEntity c WHERE cg.memberId = :memberId")
    List<ChatroomGroupVO> getListChatroomByMemberId(Long memberId);
}
