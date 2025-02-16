package com.example.springboot.service;

import com.example.springboot.dao.*;
import com.example.springboot.dao.querydsl.MemberSmsQueryDSLDAO;
import com.example.springboot.dto.*;
import com.example.springboot.entity.common.util.ColumnYn;
import com.example.springboot.entity.domain.*;
import com.example.springboot.exception.*;
import com.example.springboot.util.CommonCodeEnum;
import com.example.springboot.util.ExceptionCodeEnum;
import com.example.springboot.util.ResultCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final EncryptionService encryptionService;
    private final PasswordEncoder passwordEncoder;
    private final MemberDAO memberDAO;
    private final MemberSmsDAO memberSmsDAO;
    private final MemberEmailDAO memberEmailDAO;
    private final SelectedTermsDAO selectedTermsDAO;
    private final TermsOfUseDAO termsOfUseDAO;
    private final MemberSmsQueryDSLDAO memberSmsQueryDSLDAO;
    private final AuthTokensGenerator authTokensGenerator;
    private final ChatroomService chatroomService;
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 회원가입
     * <p>
     * 회원가입을 위한 필수체크 및 유저 정보를 저장하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    @Override
    public MemberSignupResponseDTO signup(MemberSignupRequestDTO memberSignupRequestDTO) {
        log.info("signup memberSignupRequestDTO : {}", memberSignupRequestDTO);

        // 아이디중복 체크
        _checkDuplicatedMemberId(memberSignupRequestDTO);

        // 비밀번호 체크
        _checkPassword(memberSignupRequestDTO);

        // 휴대폰인증 확인
        _checkVerifiedPhone(memberSignupRequestDTO);

        // 이메일인증 확인
        _checkVerifiedEmail(memberSignupRequestDTO);

        // 이용약관 체크
        _checkTermsOfUse(memberSignupRequestDTO);

        // 회원가입 - 멤버, 휴대폰, 이메일, 이용약관동의내역 저장
        MemberEntity memberEntity = memberDAO.save(MemberEntity.builder()
                .id(memberSignupRequestDTO.getId())
                .password(passwordEncoder.encode(memberSignupRequestDTO.getPassword()))
                .name(memberSignupRequestDTO.getName())
                .introduce(memberSignupRequestDTO.getIntroduce()).build());

        memberSmsDAO.save(MemberSmsEntity.builder()
                .phoneFirst(memberSignupRequestDTO.getPhoneFirst())
                .phoneMiddle(memberSignupRequestDTO.getPhoneMiddle())
                .phoneLast(encryptionService.encrypt(memberSignupRequestDTO.getPhoneLast()))
                .memberEntity(memberEntity)
                .phoneVerificationCode(memberSignupRequestDTO.getPhoneVerificationCode())
                .phoneVerifiedStatus(ColumnYn.valueOf(CommonCodeEnum.YES.getValue())).build());

        memberEmailDAO.save(MemberEmailEntity.builder()
                .email(memberSignupRequestDTO.getEmail())
                .memberEntity(memberEntity)
                .emailVerificationCode(memberSignupRequestDTO.getEmailVerificationCode())
                .emailVerifiedStatus(ColumnYn.valueOf(CommonCodeEnum.YES.getValue())).build());

        chatroomService.createChatroom(memberSignupRequestDTO.getId(), new ChatroomRequestDTO(100L, "AI 챗봇"));

        memberSignupRequestDTO.getTermsAgreementDTOList().forEach(termsAgreementDTO -> {
            TermsOfUseEntity termsOfUseEntity = termsOfUseDAO.findById(termsAgreementDTO.getTermsOfUseId()).orElseThrow(() -> new FailGetTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE));

            selectedTermsDAO.save(SelectedTermsEntity.builder()
                    .memberEntity(memberEntity)
                    .termsOfUseEntity(termsOfUseEntity).build());
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
    private void _checkDuplicatedMemberId(MemberSignupRequestDTO memberSignupRequestDTO) {
        memberDAO.findById(memberSignupRequestDTO.getId()).ifPresent(memberEntity -> {
            throw new DuplicatedIdException(ExceptionCodeEnum.DUPLICATED_ID);
        });
    }

    /**
     * 비밀번호 체크
     * <p>
     * 입력한 비밀번호와 비밀번호 확인을 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private static void _checkPassword(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!memberSignupRequestDTO.getPassword().equals(memberSignupRequestDTO.getPasswordConfirmation())) {
            throw new MismatchPasswordException(ExceptionCodeEnum.MISMATCH_PASSWORD);
        }
    }

    /**
     * 휴대폰인증 확인
     * <p>
     * 휴대폰인증이 되었는지 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void _checkVerifiedPhone(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!CommonCodeEnum.YES.getValue().equals(memberSignupRequestDTO.getPhoneVerifiedStatus())) {
            throw new FailVerifiedPhoneException(ExceptionCodeEnum.UNVERIFIED_PHONE);
        }
    }

    /**
     * 이메일인증 확인
     * <p>
     * 이메일인증이 되었는지 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void _checkVerifiedEmail(MemberSignupRequestDTO memberSignupRequestDTO) {
        if (!CommonCodeEnum.YES.getValue().equals(memberSignupRequestDTO.getEmailVerifiedStatus())) {
            throw new FailVerifiedEmailException(ExceptionCodeEnum.UNVERIFIED_EMAIL);
        }
    }

    /**
     * 이용약관 체크
     * <p>
     * 이용약관 필수값들을 체크하는 메소드이다.
     *
     * @param memberSignupRequestDTO
     */
    private void _checkTermsOfUse(MemberSignupRequestDTO memberSignupRequestDTO) {

        List<TermsOfUseEntity> termsOfUseEntityList = termsOfUseDAO.findByRequired(ColumnYn.valueOf(CommonCodeEnum.YES.getValue()));

        if (termsOfUseEntityList.isEmpty() || memberSignupRequestDTO.getTermsAgreementDTOList() == null || memberSignupRequestDTO.getTermsAgreementDTOList().isEmpty()) {
            throw new FailGetTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_TERMS_OF_USE);
        }

        Set<Long> requiredTermsOfUseIds = termsOfUseEntityList.stream().map(TermsOfUseEntity::getTermsOfUseId).collect(Collectors.toSet());

        memberSignupRequestDTO.getTermsAgreementDTOList().forEach(termsAgreementDTO -> {
            if (!requiredTermsOfUseIds.contains(termsAgreementDTO.getTermsOfUseId())) {
                if (CommonCodeEnum.YES.getValue().equals(termsAgreementDTO.getRequired().toString())) {
                    throw new FailSaveRequiredTermsOfUseException(ExceptionCodeEnum.NONEXISTENT_REQUIRED_TERMS_OF_USE);
                }
            }
        });
    }

    /**
     * 로그인
     * <p>
     * 아이디, 비밀번호를 검증하여 로그인 토큰을 만드는 메소드이다.
     *
     * @param memberLoginRequestDTO
     * @return
     */
    @Override
    public MemberLoginResponseDTO login(MemberLoginRequestDTO memberLoginRequestDTO) {
        log.info("login memberLoginRequestDTO: {}", memberLoginRequestDTO);

        return memberDAO.findById(memberLoginRequestDTO.getId()).map(
                        memberEntity -> {
                            if (!passwordEncoder.matches(memberLoginRequestDTO.getPassword(), memberEntity.getPassword())) {
                                throw new ErrorIdAndPasswordException(ExceptionCodeEnum.MISMATCH_ID_OR_PASSWORD);
                            }

                            if (CommonCodeEnum.NO.getValue().equals(String.valueOf(memberEntity.getMemberStatus()))) {
                                throw new WithdrawalOfMemberException(ExceptionCodeEnum.WITHDRAWAL_MEMBER);
                            }

                            if (memberEntity.getPasswordFailureCount() >= CommonCodeEnum.FIVE.getNum()) {
                                throw new ErrorFiveTimesOverPasswordException(ExceptionCodeEnum.ERROR_FIVE_TIMES_OVER_PASSWORD);
                            }

                            memberDAO.resetPasswordFailureCount(memberEntity.getId());

                            AuthTokensDTO authTokensDTO = authTokensGenerator.generate(memberEntity.getId(), memberEntity.getMemberType(), memberEntity.getMemberId());

                            redisTemplate.opsForValue().set(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId(), authTokensDTO.getRefreshToken(), authTokensDTO.getRefreshTokenExpireTime(), TimeUnit.MILLISECONDS);

                            return MemberLoginResponseDTO.builder()
                                    .accessToken(authTokensDTO.getAccessToken())
                                    .refreshToken(authTokensDTO.getRefreshToken())
                                    .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                    .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
                        })
                .orElseThrow(() -> new ErrorIdAndPasswordException(ExceptionCodeEnum.MISMATCH_ID_OR_PASSWORD));
    }

    /**
     * 로그아웃
     * <p>
     * 로그아웃을 통해 Redis 블랙리스트 처리하는 메소드이다.
     *
     * @param memberLogoutRequestDTO
     * @return
     */
    @Override
    public MemberLogoutResponseDTO logout(MemberLogoutRequestDTO memberLogoutRequestDTO) {
        log.info("logout memberLogoutRequestDTO: {}", memberLogoutRequestDTO);

        if (authTokensGenerator.validateTokens(memberLogoutRequestDTO.getAccessToken())) {
            return memberDAO.findById(authTokensGenerator.getMemberId(memberLogoutRequestDTO.getAccessToken())).map(
                            memberEntity -> {
                                if (redisTemplate.opsForValue().get(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId()) != null) {
                                    redisTemplate.delete(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId());
                                }

                                return MemberLogoutResponseDTO.builder()
                                        .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                        .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
                            })
                    .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));
        } else {
            throw new SessionExpiredException(ExceptionCodeEnum.SESSION_EXPIRED);
        }
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
    public MemberFindIdDTO findId(String name, String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode) {
        log.info("findId name : {}, phoneFirst : {}, phoneMiddle : {}, phoneLast : {}, phoneVerificationCode : {}", name, phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);

        // 휴대폰인증 확인
        _checkVerifiedPhone(phoneFirst, phoneMiddle, phoneLast, phoneVerificationCode);

        // 멤버 조회
        List<MemberSmsEntity> memberSmsEntityList = memberSmsQueryDSLDAO.selectListMemberByPhone(phoneFirst, phoneMiddle);

        if (memberSmsEntityList.isEmpty()) {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER);
        }

        // 멤버 할당
        List<MemberIdDTO> memberIdDTOList = memberSmsEntityList.stream()
                .map(memberSmsEntity -> {
                    if (!Objects.equals(phoneLast, encryptionService.decrypt(memberSmsEntity.getPhoneLast()))) {
                        return null;
                    }

                    return MemberIdDTO.builder()
                            .memberId(memberSmsEntity.getMemberEntity().getMemberId())
                            .snsType(memberSmsEntity.getMemberEntity().getSnsType())
                            .createdAt(memberSmsEntity.getMemberEntity().getCreatedAt()).build();
                })
                .filter(Objects::nonNull)
                .toList();

        if (memberIdDTOList.isEmpty()) {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER);
        }
        return MemberFindIdDTO.builder()
                .memberIdDTOList(memberIdDTOList)
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
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
    private void _checkVerifiedPhone(String phoneFirst, String phoneMiddle, String phoneLast, String phoneVerificationCode) {
        List<MemberSmsEntity> memberSmsEntityList = memberSmsQueryDSLDAO.selectListMemberSmsByPhoneVerificationCode(phoneFirst, phoneMiddle, phoneVerificationCode);

        if (memberSmsEntityList.isEmpty()) {
            throw new FailSaveVerifiedPhoneException(ExceptionCodeEnum.NONEXISTENT_VERIFIED_PHONE);
        }

        String phoneVerifiedStatus = memberSmsEntityList.stream()
                .map(memberSmsEntity -> {
                    if (Objects.equals(phoneLast, encryptionService.decrypt(memberSmsEntity.getPhoneLast()))) {
                        return String.valueOf(memberSmsEntity.getPhoneVerifiedStatus());
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        if (phoneVerifiedStatus == null) {
            throw new FailSaveVerifiedPhoneException(ExceptionCodeEnum.NONEXISTENT_VERIFIED_PHONE);
        }

        if (!CommonCodeEnum.YES.getValue().equals(phoneVerifiedStatus)) {
            throw new FailVerifiedPhoneException(ExceptionCodeEnum.UNVERIFIED_PHONE);
        }
    }

    /**
     * 비밀번호 찾기
     * <p>
     * 아이디와 이름, 휴대폰번호로 비밀번호를 변경하는 메소드이다.
     *
     * @param memberFindPasswordRequestDTO
     * @return
     */
    @Override
    public MemberFindPasswordResponseDTO findPassword(MemberFindPasswordRequestDTO memberFindPasswordRequestDTO) {
        log.info("findPassword memberFindPasswordRequestDTO : {}", memberFindPasswordRequestDTO);

        // 휴대폰인증 확인
        _checkVerifiedPhone(memberFindPasswordRequestDTO.getPhoneFirst(), memberFindPasswordRequestDTO.getPhoneMiddle(), memberFindPasswordRequestDTO.getPhoneLast(), memberFindPasswordRequestDTO.getPhoneVerificationCode());

        // 멤버 조회
        List<MemberSmsEntity> memberSmsEntityList = memberSmsQueryDSLDAO.selectListMemberByIdAndNameAndPhone(memberFindPasswordRequestDTO.getId(), memberFindPasswordRequestDTO.getName(), memberFindPasswordRequestDTO.getPhoneFirst(), memberFindPasswordRequestDTO.getPhoneMiddle());

        if (memberSmsEntityList.isEmpty()) {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER);
        }

        boolean isPasswordChanged = memberSmsEntityList.stream()
                .anyMatch(memberSmsEntity ->
                        Objects.equals(memberFindPasswordRequestDTO.getPhoneLast(), encryptionService.decrypt(memberSmsEntity.getPhoneLast()))
                );

        if (isPasswordChanged) {
            // 비밀번호 변경
            if (Objects.equals(memberFindPasswordRequestDTO.getPassword(), memberFindPasswordRequestDTO.getPasswordConfirmation())) {
                memberDAO.save(memberFindPasswordRequestDTO.getId(), passwordEncoder.encode(memberFindPasswordRequestDTO.getPassword()));
            } else {
                throw new MismatchPasswordException(ExceptionCodeEnum.MISMATCH_PASSWORD);
            }
        } else {
            throw new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER);
        }

        return MemberFindPasswordResponseDTO.builder()
                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
    }

    /**
     * 로그인 갱신
     * <p>
     * 엑세스토큰을 검증하고 로그인을 갱신하는 메소드이다.
     *
     * @param memberLoginRenewRequestDTO
     * @return
     */
    @Override
    public MemberLoginRenewResponseDTO loginRenew(MemberLoginRenewRequestDTO memberLoginRenewRequestDTO) {
        if (authTokensGenerator.validateTokens(memberLoginRenewRequestDTO.getRefreshToken())) {
            return memberDAO.findById(authTokensGenerator.getMemberId(memberLoginRenewRequestDTO.getRefreshToken())).map(
                            memberEntity -> {
                                if (CommonCodeEnum.YES.getValue().equals(String.valueOf(memberEntity.getAutoLogin()))) {
                                    AuthTokensDTO authTokensDTO = authTokensGenerator.generate(memberEntity.getId(), memberEntity.getMemberType(), memberEntity.getMemberId());

                                    redisTemplate.opsForValue().set(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId(), authTokensDTO.getRefreshToken(), authTokensDTO.getRefreshTokenExpireTime(), TimeUnit.MILLISECONDS);

                                    return MemberLoginRenewResponseDTO.builder()
                                            .accessToken(authTokensDTO.getAccessToken())
                                            .refreshToken(authTokensDTO.getRefreshToken())
                                            .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                            .resultMessage(ResultCodeEnum.SUCCESS.getMessage()).build();
                                } else {
                                    if (redisTemplate.opsForValue().get(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId()) != null) {
                                        redisTemplate.delete(CommonCodeEnum.REDIS_KEY.getValue() + memberEntity.getMemberId());
                                    }

                                    throw new SessionExpiredException(ExceptionCodeEnum.SESSION_EXPIRED);
                                }
                            })
                    .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));
        } else {
            throw new SessionExpiredException(ExceptionCodeEnum.SESSION_EXPIRED);
        }
    }

    /**
     * 멤버정보 조회
     * <p>
     * 멤버정보를 조회하는 메소드이다.
     *
     * @param memberId
     * @return
     */
    @Override
    public MemberInfoDTO memberInfo(Long memberId) {
        return memberDAO.findById(memberId).stream()
                .map(memberEntity ->
                        MemberInfoDTO.builder()
                                .memberId(memberEntity.getMemberId())
                                .nickname(memberEntity.getNickname())
                                .profileImageUrl(memberEntity.getProfileImageUrl())
                                .answerTime(memberEntity.getAnswerTime())
                                .resultCode(ResultCodeEnum.SUCCESS.getValue())
                                .resultMessage(ResultCodeEnum.SUCCESS.getMessage())
                                .build()
                )
                .findFirst()
                .orElseThrow(() -> new FailGetMemberException(ExceptionCodeEnum.NONEXISTENT_MEMBER));
    }
}
