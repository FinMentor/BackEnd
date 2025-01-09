package com.example.springboot.service;

import com.example.springboot.dto.UploadImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    UploadImageDTO uploadImage(String id, MultipartFile multipartFile);
}
