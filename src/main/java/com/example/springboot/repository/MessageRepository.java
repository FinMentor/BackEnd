package com.example.springboot.repository;

import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    @Query(value = "SELECT ms.message_id, ms.chatroom_id, ms.member_id, ms.content, ms.message_type, ms.created_at, ms.updated_at, " +
            "m.nickname, m.profile_image_url " +
            "FROM MESSAGE ms " +
            "JOIN MEMBER m ON ms.member_id = m.member_id " +
            "WHERE ms.chatroom_id = :chatroomId",
            countQuery = "SELECT COUNT(*) FROM MESSAGE ms WHERE ms.chatroom_id = :chatroomId",
            nativeQuery = true)
    Page<MessageEntity> findByChatroomId(@Param("chatroomId") Long chatroomId, Pageable pageable);

    void deleteByChatroomId(Long chatroomId);
}
