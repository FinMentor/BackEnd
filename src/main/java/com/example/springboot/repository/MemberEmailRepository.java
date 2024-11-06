package com.example.springboot.repository;

import com.example.springboot.entity.MemberEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEmailRepository extends JpaRepository<MemberEmailEntity, Integer> {

}
