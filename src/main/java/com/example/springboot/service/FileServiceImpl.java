package com.example.springboot.service;

import com.example.springboot.dao.FileDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dto.UploadImageDTO;
import com.example.springboot.entity.domain.FileEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    private final MemberDAO memberDAO;
    private final FileDAO fileDAO;

    @Override
    public UploadImageDTO uploadImage(String id, MultipartFile multipartFile) throws IOException {
        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        String filePath = "/Volumes/bbaek2.synology.me/NAS_WEB_SERVER/" + UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename();

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());
        Files.write(path, multipartFile.getBytes());

        fileDAO.save(FileEntity.builder()
                .memberEntity(memberEntity)
                .filePath(filePath).build());

        return UploadImageDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
