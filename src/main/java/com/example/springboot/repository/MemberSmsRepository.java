package com.example.springboot.repository;

import com.example.springboot.entity.domain.MemberSmsEntity;
import com.example.springboot.entity.id.MemberSmsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSmsRepository extends JpaRepository<MemberSmsEntity, MemberSmsId> {

}
