package com.example.springboot.repository;

import com.example.springboot.vo.MemberAnswerVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAnswerRepository extends JpaRepository<MemberAnswerVO, Integer> {

}
