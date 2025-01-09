package com.example.springboot.controller;

import com.example.springboot.dto.UploadImageDTO;
import com.example.springboot.service.FileService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseResult<UploadImageDTO> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        log.info("uploadImage multipartFile : {}", multipartFile);

        return ResponseResult.success(fileService.uploadImage(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), multipartFile));
    }
}
