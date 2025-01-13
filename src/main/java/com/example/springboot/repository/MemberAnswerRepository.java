package com.example.springboot.repository;

import com.example.springboot.vo.id.MemberAnswerId;
import com.example.springboot.vo.MemberAnswerVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAnswerRepository extends JpaRepository<MemberAnswerVO, MemberAnswerId> {

}
