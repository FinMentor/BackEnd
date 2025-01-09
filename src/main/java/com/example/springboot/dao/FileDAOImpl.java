package com.example.springboot.dao;

import com.example.springboot.entity.domain.FileEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.FileRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileDAOImpl implements FileDAO {
    private final FileRepository fileRepository;

    @Override
    public FileEntity save(FileEntity fileEntity) {
        if (fileEntity.getMemberEntity() == null || fileEntity.getMemberEntity().getMemberId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (fileEntity.getFilePath() == null || fileEntity.getFilePath().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("filePath는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save fileEntity: {}", fileEntity);

        return fileRepository.save(fileEntity);
    }
}
