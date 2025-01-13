package com.example.springboot.controller;

import com.example.springboot.dto.FileImageDTO;
import com.example.springboot.dto.UploadImageDTO;
import com.example.springboot.service.FileService;
import com.example.springboot.util.ResponseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {
    private final FileService fileService;

    /**
     * 이미지 업로드
     * <p>
     * 이미지 정보를 가져와 업로드하는 메소드이다.
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseResult<UploadImageDTO> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        log.info("uploadImage multipartFile : {}", multipartFile);

        return ResponseResult.success(fileService.uploadImage(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), multipartFile));
    }

    /**
     * 이미지 조회
     * <p>
     * 사용자 정보를 가져와 이미지 URL을 조회하는 메소드이다.
     *
     * @param userDetails
     * @return
     */
    @GetMapping("/image")
    public ResponseResult<FileImageDTO> getImage(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("getImage userDetails : {}", userDetails);

        return ResponseResult.success(fileService.getImage(userDetails.getUsername()));
    }
}
