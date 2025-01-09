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

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    private final MemberDAO memberDAO;
    private final FileDAO fileDAO;

    @Override
    public UploadImageDTO uploadImage(String id, MultipartFile multipartFile) {
        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        fileDAO.save(FileEntity.builder()
                .memberEntity(memberEntity)
                .filePath("/Volumes/bbaek2.synology.me/NAS_WEB_SERVER/" + UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename()).build());

        return UploadImageDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
