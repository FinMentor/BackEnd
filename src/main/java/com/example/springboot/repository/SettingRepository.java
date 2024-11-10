package com.example.springboot.repository;

import com.example.springboot.entity.domain.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, Long> {

}
