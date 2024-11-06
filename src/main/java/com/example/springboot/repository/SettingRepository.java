package com.example.springboot.repository;

import com.example.springboot.entity.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingRepository extends JpaRepository<SettingEntity, Integer> {

}
