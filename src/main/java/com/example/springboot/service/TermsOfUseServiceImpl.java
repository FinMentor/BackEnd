package com.example.springboot.service;

import com.example.springboot.dto.TermsOfUseDTO;
import com.example.springboot.entity.TermsOfUseEntity;
import com.example.springboot.repository.TermsOfUseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TermsOfUseServiceImpl implements TermsOfUseService {
    private final TermsOfUseRepository termsOfUseRepository;

    @Override
    public List<TermsOfUseDTO> getTermsOfUse() {
        List<TermsOfUseEntity> termsOfUseEntityList = termsOfUseRepository.selectListTermsOfUse();

        if (log.isInfoEnabled()) {
            log.info("getTermsOfUse termsOfUseEntityList : {}", termsOfUseEntityList.toString());
        }

        return termsOfUseEntityList.stream()
                .map(termsOfUse -> TermsOfUseDTO.builder()
                        .termOfUseId(termsOfUse.getTermsOfUseId())
                        .title(termsOfUse.getTitle())
                        .content(termsOfUse.getContent())
                        .required(termsOfUse.getRequired())
                        .build())
                .collect(Collectors.toList());
    }
}
