package com.example.springboot.repository;

import com.example.springboot.entity.domain.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    Page<MessageEntity> findByChatroomId(Long chatroomId, Pageable pageable);
    void deleteByChatroomId(Long chatroomId);
}
