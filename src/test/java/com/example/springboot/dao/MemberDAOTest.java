package com.example.springboot.dao;

import com.example.springboot.entity.domain.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemberDAOTest {
    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void findById() {
        String memberId = "testUser01";
        Optional<MemberEntity> optionalMemberEntity = memberDAO.findById(memberId);
        assertTrue(optionalMemberEntity.isPresent());
    }

    @Test
    public void save() {
        String memberId = "testUser01";
        String password = "123456";
        String name = "금길동";

        MemberEntity memberEntity = memberDAO.save(MemberEntity.builder()
                .memberId(memberId)
                .password(passwordEncoder.encode(password))
                .name(name).build());
        assertNotNull(memberEntity);
    }
}
