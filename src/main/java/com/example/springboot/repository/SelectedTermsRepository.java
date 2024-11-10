package com.example.springboot.repository;

import com.example.springboot.entity.domain.SelectedTermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedTermsRepository extends JpaRepository<SelectedTermsEntity, Long> {

}
