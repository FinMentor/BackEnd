package com.example.springboot.dao;

import com.example.springboot.entity.domain.FileEntity;

public interface FileDAO {
    FileEntity save(FileEntity fileEntity);
}
