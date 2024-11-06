package com.example.springboot.service;

import com.example.springboot.dto.MemberProfileDTO;
import com.example.springboot.entity.MemberEntity;
import com.example.springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberProfileServiceImpl implements MemberProfileService {
    private final MemberRepository memberRepository;

    @Override
    public MemberProfileDTO getMemberProfile(String memberId) {
        try {
            MemberEntity memberProfile = memberRepository.selectMemberProfile(memberId);

            if (memberProfile == null) {
                log.warn("Member not found for memberId: {}", memberId);
                return null;
            }

            // Member 객체의 프로필 정보를 MemberDTO 로 변환
            MemberProfileDTO memberDTO = MemberProfileDTO.builder()
                    .memberId(memberProfile.getMemberId())
                    .nickname(memberProfile.getNickname())
                    .profileImageUrl(memberProfile.getProfileImageUrl())
                    .name(memberProfile.getName())
                    .build();

            log.info("Member profile and total assets retrieved: {}", memberDTO);
            return memberDTO;

        } catch (Exception e) {
            log.error("Failed to retrieve member profile for memberId: {}", memberId, e);
            return null;
        }
    }
}
