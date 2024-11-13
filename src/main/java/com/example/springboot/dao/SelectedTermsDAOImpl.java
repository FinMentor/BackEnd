package com.example.springboot.dao;

import com.example.springboot.entity.domain.SelectedTermsEntity;
import com.example.springboot.exception.ErrorRequiredValueValidationException;
import com.example.springboot.repository.SelectedTermsRepository;
import com.example.springboot.util.ExceptionCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SelectedTermsDAOImpl implements SelectedTermsDAO {
    private final SelectedTermsRepository selectedTermsRepository;

    /**
     * 이용약관동의내역 저장
     * <p>
     * 이용약관동의내역 테이블에 컬럼 정보를 저장하는 메소드이다.
     *
     * @param selectedTermsEntity
     * @return
     */
    @Override
    public SelectedTermsEntity save(SelectedTermsEntity selectedTermsEntity) {
        if (selectedTermsEntity.getMemberEntity() == null || selectedTermsEntity.getMemberEntity().getMemberId() == null || selectedTermsEntity.getMemberEntity().getMemberId().isEmpty()) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("memberId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        if (selectedTermsEntity.getTermsOfUseEntity() == null || selectedTermsEntity.getTermsOfUseEntity().getTermsOfUseId() == null) {
            throw new ErrorRequiredValueValidationException(new StringBuilder("termsOfUseId는 "), ExceptionCodeEnum.NONEXISTENT_REQUIRED_VALUE);
        }

        log.info("save selectedTermsEntity : {}", selectedTermsEntity);

        return selectedTermsRepository.save(selectedTermsEntity);
    }
}
