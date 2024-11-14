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
        String id = "testUser1";
        Optional<MemberEntity> optionalMemberEntity = memberDAO.findById(id);
        assertTrue(optionalMemberEntity.isPresent());
    }

    @Test
    public void save() {
        String id = "testUser1";
        String password = "123456";
        String name = "김길동";
        String introduce = "안녕하세요.";

        MemberEntity memberEntity = memberDAO.save(MemberEntity.builder()
                .id(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .introduce(introduce).build());
        assertNotNull(memberEntity);
    }
}
