package com.example.springboot.dao;

import com.example.springboot.entity.domain.FileEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.FileRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileDAOImpl implements FileDAO {
    private final FileRepository fileRepository;

    /**
     * 이미지 저장
     * <p>
     * 파일에 이미지를 저장하는 메소드이다.
     *
     * @param fileEntity
     */
    @Override
    public void insert(FileEntity fileEntity) {
        if (fileEntity.getMemberEntity() == null || fileEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (fileEntity.getFilePath() == null || fileEntity.getFilePath().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("filePath는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("insert fileEntity: {}", fileEntity);

        fileRepository.save(fileEntity);
    }

    /**
     * 이미지 수정
     * <p>
     * 파일에 이미지를 수정하는 메소드이다.
     *
     * @param fileEntity
     */
    @Override
    public void update(FileEntity fileEntity) {
        if (fileEntity.getFileId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("fileId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (fileEntity.getMemberEntity() == null || fileEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (fileEntity.getFilePath() == null || fileEntity.getFilePath().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("filePath는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("update fileEntity: {}", fileEntity);

        fileRepository.save(fileEntity);
    }

    /**
     * 이미지경로 조회
     * <p>
     * 멤버아이디로 이미지경로를 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public List<FileEntity> findByMemberId(Long memberId) {
        if (memberId == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("findByMemberId memberId: {}", memberId);

        return fileRepository.findByMemberId(memberId);
    }
}
