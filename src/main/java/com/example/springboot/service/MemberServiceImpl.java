package com.example.springboot.service;

import com.example.springboot.dto.MemberFindidResponseDTO;
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

import java.util.ArrayList;
import java.util.List;
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
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                .memberId(memberSignupRequestDTO.getMemberId())
                .password(passwordEncoder.encode(memberSignupRequestDTO.getPassword()))
                .name(memberSignupRequestDTO.getName()).build());

        memberSmsRepository.save(MemberSmsEntity.builder()
                .phoneFirst(memberSignupRequestDTO.getPhoneFirst())
                .phoneMiddle(memberSignupRequestDTO.getPhoneMiddle())
                .phoneLast(encryptionService.encrypt(memberSignupRequestDTO.getPhoneLast()))
                .memberEntity(memberEntity)
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

        List<TermsOfUseEntity> termsOfUseEntityList = termsOfUseRepository.findByRequired(CommonCodeEnum.YES.getValue().charAt(0));

        if (termsOfUseEntityList.isEmpty() || memberSignupRequestDTO.getTermsAgreementDTOList() == null || memberSignupRequestDTO.getTermsAgreementDTOList().isEmpty()) {
            throw new FailGetTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE.getMessage());
        }

        Set<Long> requiredTermsOfUseIds = termsOfUseEntityList.stream().map(TermsOfUseEntity::getTermsOfUseId).collect(Collectors.toSet());

        memberSignupRequestDTO.getTermsAgreementDTOList().forEach(termsAgreementDTO -> {
            if (!requiredTermsOfUseIds.contains(termsAgreementDTO.getTermsOfUseId())) {
                if (CommonCodeEnum.YES.getValue().equals(termsAgreementDTO.getRequired().toString())) {
                    throw new FailSaveRequiredTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE.getMessage());
                }
            }
        });
    }

    /**
     * 아이디 찾기
     * <p>
     * 이름과 휴대폰으로 아이디를 찾는 메소드이다.
     *
     * @param name
     * @param phoneFirst
     * @param phoneMiddle
     * @param phoneLast
     * @param phoneVerificationCode
     * @return
     */
    @Override
    public List<MemberFindidResponseDTO> findid(String name, String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode) {
        if (log.isInfoEnabled()) {
            log.info("findId name : {}, phoneFirst : {}, phoneMiddle : {}, phoneLast : {}, phoneVerificationCode : {}", name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);
        }

        // 휴대폰인증 확인
        checkVerifiedPhone(phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);

        // 멤버 조회
        List<MemberEntity> memberEntityList = memberRepository.findMemberByPhone(phoneFirst, phoneMiddle);

        if (memberEntityList.isEmpty()) {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER.getMessage());
        }

        // 멤버 할당
        List<MemberFindidResponseDTO> memberFindidResponseDTOList = new ArrayList<>();
        memberEntityList.forEach(memberEntity -> {
            if (phoneLast.equals(encryptionService.decrypt(memberEntity.getMemberSmsEntity().getPhoneLast()))) {
                MemberFindidResponseDTO memberFindidResponseDTO = MemberFindidResponseDTO.builder()
                        .memberId(memberEntity.getMemberId())
                        .snsType(memberEntity.getSnsType())
                        .createdAt(memberEntity.getCreatedAt()).build();
                memberFindidResponseDTOList.add(memberFindidResponseDTO);
            }
        });

        if (memberFindidResponseDTOList.isEmpty()) {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER.getMessage());
        }
        return memberFindidResponseDTOList;
    }

    /**
     * 휴대폰인증 확인
     * <p>
     * 휴대폰인증이 되었는지 체크하는 메소드이다.
     *
     * @param phoneFirst
     * @param phoneMiddle
     * @param phoneLast
     * @param phoneVerificationCode
     */
    private void checkVerifiedPhone(String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode) {
        List<MemberSmsEntity> memberSmsEntityList = memberSmsRepository.findByPhoneFirstAndPhoneMiddleAndPhoneVerificationCode(phoneFirst, phoneMiddle, phoneVerificationCode);

        if (memberSmsEntityList.isEmpty()) {
            throw new FailSaveVerifiedPhoneException(ExceptionCodeEnum.NONEXISTENT_VERIFIED_PHONE.getMessage());
        }

        Character phoneVerifiedStatus = null;
        for (MemberSmsEntity memberSmsEntity : memberSmsEntityList) {
            if (phoneLast.equals(encryptionService.decrypt(memberSmsEntity.getPhoneLast()))) {
                phoneVerifiedStatus = memberSmsEntity.getPhoneVerifiedStatus();
            }
        }

        if (phoneVerifiedStatus == null) {
            throw new FailSaveVerifiedPhoneException(ExceptionCodeEnum.NONEXISTENT_VERIFIED_PHONE.getMessage());
        }

        if (!CommonCodeEnum.YES.getValue().equals(Character.toString(phoneVerifiedStatus))) {
            throw new FailVerifiedPhoneException(ExceptionCodeEnum.UNVERIFIED_PHONE.getMessage());
        }
    }
}
