package com.example.springboot.repository;

import com.example.springboot.entity.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
