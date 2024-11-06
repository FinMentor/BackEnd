package com.example.springboot.repository;

import com.example.springboot.entity.MemberSmsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSmsRepository extends JpaRepository<MemberSmsEntity, Integer> {

}
