package com.example.springboot.repository;

import com.example.springboot.entity.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Integer> {
}
