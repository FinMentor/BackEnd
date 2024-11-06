package com.example.springboot.repository;

import com.example.springboot.entity.TermsOfUseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsOfUseRepository extends JpaRepository<TermsOfUseEntity, Integer> {

}
