package com.example.springboot.service;

import com.example.springboot.dto.MemberSignupRequestDTO;
import com.example.springboot.dto.MemberSignupResponseDTO;
import com.example.springboot.entity.*;
import com.example.springboot.exception.*;
import com.example.springboot.repository.*;
import com.example.springboot.util.CommonCodeEnum;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final EncryptionService encryptionService;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberSmsRepository memberSmsRepository;
    private final MemberEmailRepository memberEmailRepository;
    private final SelectedTermsRepository selectedTermsRepository;
    private final TermsOfUseRepository termsOfUseRepository;

    /**
     * 회원가입
     * <p>
     * 회원가입을 위한 필수체크 및 유저 정보를 저장하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    @Override
    public MemberSignupResponseDTO signup(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (log.isInfoEnabled()) {
            log.info("signup memberSignupRequestDTO : {}", memberSignupRequestDTO);
        }

        // 아이디중복 체크
        checkDuplicatedMemberId(memberSignupRequestDTO);

        // 비밀번호 체크
        checkPassword(memberSignupRequestDTO);

        // 휴대폰인증 확인
        checkVerifiedPhone(memberSignupRequestDTO);

        // 이메일인증 확인
        checkVerifiedEmail(memberSignupRequestDTO);

        // 이용약관 체크
        checkTermsOfUse(memberSignupRequestDTO);

        // 회원가입 - 멤버, 휴대폰, 이메일, 이용약관동의내역 저장
        memberRepository.save(MemberEntity.builder()
                .memberId(memberSignupRequestDTO.getMemberId())
                .password(passwordEncoder.encode(memberSignupRequestDTO.getPassword()))
                .name(memberSignupRequestDTO.getName()).build());

        memberSmsRepository.save(MemberSmsEntity.builder()
                .phoneFirst(memberSignupRequestDTO.getPhoneFirst())
                .phoneMiddle(memberSignupRequestDTO.getPhoneMiddle())
                .phoneLast(encryptionService.encrypt(memberSignupRequestDTO.getPhoneLast()))
                .memberId(memberSignupRequestDTO.getMemberId())
                .phoneVerificationCode(memberSignupRequestDTO.getPhoneVerificationCode())
                .phoneVerifiedStatus(CommonCodeEnum.YES.getValue().charAt(0)).build());

        memberEmailRepository.save(MemberEmailEntity.builder()
                .email(memberSignupRequestDTO.getEmail())
                .memberId(memberSignupRequestDTO.getMemberId())
                .emailVerificationCode(memberSignupRequestDTO.getEmailVerificationCode())
                .emailVerifiedStatus(CommonCodeEnum.YES.getValue().charAt(0)).build());

        memberSignupRequestDTO.getTermsAgreementDTOList().forEach(termsAgreementDTO -> {
            selectedTermsRepository.save(SelectedTermsEntity.builder()
                    .memberId(memberSignupRequestDTO.getMemberId())
                    .termsOfUseId(termsAgreementDTO.getTermsOfUseId()).build());
        });
        return MemberSignupResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                .build();
    }

    /**
     * 아이디중복 체크
     * <p>
     * 아이디로 중복 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void checkDuplicatedMemberId(MemberSignupRequestDTO memberSignupRequestDTO) {
        memberRepository.findByMemberId(memberSignupRequestDTO.getMemberId()).ifPresent(memberEntity -> {
            throw new DuplicatedIdException(ExceptionCodeEnum.DUPLICATED_ID.getMessage());
        });
    }

    /**
     * 비밀번호 체크
     * <p>
     * 입력한 비밀번호와 비밀번호 확인을 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private static void checkPassword(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!memberSignupRequestDTO.getPassword().equals(memberSignupRequestDTO.getPasswordConfirmation())) {
            throw new MismatchPasswordException(ExceptionCodeEnum.MISMATCH_PASSWORD.getMessage());
        }
    }

    /**
     * 휴대폰인증 확인
     * <p>
     * 휴대폰인증이 되었는지 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void checkVerifiedPhone(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!CommonCodeEnum.YES.getValue().equals(memberSignupRequestDTO.getPhoneVerifiedStatus())) {
            throw new FailVerifiedPhoneException(ExceptionCodeEnum.UNVERIFIED_PHONE.getMessage());
        }
    }

    /**
     * 이메일인증 확인
     * <p>
     * 이메일인증이 되었는지 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void checkVerifiedEmail(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!CommonCodeEnum.YES.getValue().equals(memberSignupRequestDTO.getEmailVerifiedStatus())) {
            throw new FailVerifiedEmailException(ExceptionCodeEnum.UNVERIFIED_EMAIL.getMessage());
        }
    }

    /**
     * 이용약관 체크
     * <p>
     * 이용약관 필수값들을 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void checkTermsOfUse(MemberSignupRequestDTO memberSignupRequestDTO) {
        Optional<TermsOfUseEntity> optionalTermsOfUseEntity = termsOfUseRepository.findByRequired(CommonCodeEnum.YES.getValue().charAt(0));

        if (optionalTermsOfUseEntity.isEmpty() || memberSignupRequestDTO.getTermsAgreementDTOList() == null || memberSignupRequestDTO.getTermsAgreementDTOList().isEmpty()) {
            throw new FailGetTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE.getMessage());
        }

        Set<Long> requiredTermsOfUseIds = optionalTermsOfUseEntity.stream().map(TermsOfUseEntity::getTermsOfUseId).collect(Collectors.toSet());

        memberSignupRequestDTO.getTermsAgreementDTOList().forEach(termsAgreementDTO -> {
            if (!requiredTermsOfUseIds.contains(termsAgreementDTO.getTermsOfUseId())) {
                if (CommonCodeEnum.YES.getValue().equals(termsAgreementDTO.getRequired().toString())) {
                    throw new FailSaveRequiredTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getMessage());
                }
            }
        });
    }
}
