package com.example.springboot.service;

import com.example.springboot.dao.FileDAO;
import com.example.springboot.dao.MemberDAO;
import com.example.springboot.dto.FileImageDTO;
import com.example.springboot.dto.UploadImageDTO;
import com.example.springboot.entity.domain.FileEntity;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.exception.FailGetFileException;
import com.example.springboot.exception.FailGetMemberException;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {
    private final MemberDAO memberDAO;
    private final FileDAO fileDAO;

    @Value("${file.username}")
    private String username;

    @Value("${file.password}")
    private String password;

    @Value("${file.image.url}")
    private String fileUrl;

    /**
     * 이미지 업로드
     * <p>
     * 이미지 정보를 가져와 업로드하는 메소드이다.
     *
     * @param id
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @Override
    public UploadImageDTO uploadImage(String id, MultipartFile multipartFile) throws IOException {
        log.info("uploadImage id : {}, multipartFile : {}", id, multipartFile);

        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        List<FileEntity> fileEntityList = fileDAO.findByMemberId(memberEntity.getMemberId());

        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<ByteArrayResource> httpEntity = new HttpEntity<>(new ByteArrayResource(multipartFile.getBytes()), httpHeaders);
        RestTemplate restTemplate = new RestTemplate();

        if (fileEntityList.isEmpty()) {
            fileDAO.insert(FileEntity.builder()
                    .memberEntity(memberEntity)
                    .fileName(fileName).build());
        } else {
            FileEntity fileEntity = fileEntityList.stream().max(Comparator.comparing(FileEntity::getCreatedAt)).orElseThrow(() -> new FailGetFileException(ExceptionCodeEnum.NONEXISTENT_FILE));

            restTemplate.exchange(fileUrl + fileEntity.getFileName(), HttpMethod.DELETE, httpEntity, Void.class);

            fileDAO.update(FileEntity.builder()
                    .fileId(fileEntity.getFileId())
                    .memberEntity(memberEntity)
                    .fileName(fileName).build());
        }

        restTemplate.exchange(fileUrl + fileName, HttpMethod.PUT, httpEntity, Void.class);

        return UploadImageDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 이미지 조회
     * <p>
     * 사용자 정보를 가져와 이미지 URL을 조회하는 메소드이다.
     *
     * @param id
     * @return
     */
    @Override
    public FileImageDTO getImage(String id) {
        log.info("getImage id : {}", id);

        MemberEntity memberEntity = memberDAO.findById(id).orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));

        FileEntity fileEntity = fileDAO.findByMemberId(memberEntity.getMemberId()).stream().max(Comparator.comparing(FileEntity::getCreatedAt)).orElseThrow(() -> new FailGetFileException(ExceptionCodeEnum.NONEXISTENT_FILE));

        return FileImageDTO.builder()
                .filePath(fileUrl + fileEntity.getFileName())
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }
}
