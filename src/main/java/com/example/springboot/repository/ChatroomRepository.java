package com.example.springboot.repository;

import com.example.springboot.entity.domain.ChatroomEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    List<ChatroomEntity> findByChatroomGroupVOList_MemberId(Long memberId);
}
