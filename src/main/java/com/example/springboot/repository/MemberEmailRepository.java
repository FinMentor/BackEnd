package com.example.springboot.repository;

import com.example.springboot.entity.domain.MemberEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEmailRepository extends JpaRepository<MemberEmailEntity, String> {

}
