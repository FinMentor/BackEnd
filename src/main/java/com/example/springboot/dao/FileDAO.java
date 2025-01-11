package com.example.springboot.dao;

import com.example.springboot.entity.domain.FileEntity;

import java.util.List;

public interface FileDAO {
    void insert(FileEntity fileEntity);

    void update(FileEntity fileEntity);

    List<FileEntity> findByMemberId(Long memberId);
}
