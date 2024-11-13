package com.example.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {
    private final BasicTextEncryptor basicTextEncryptor;

    public EncryptionServiceImpl(@Value("${jasypt.encryptor.password}") String mySecretKey) {
        this.basicTextEncryptor = new BasicTextEncryptor();
        this.basicTextEncryptor.setPassword(mySecretKey);  // 암호화 키 설정
    }

    public String encrypt(String plainText) {
        return basicTextEncryptor.encrypt(plainText);
    }

    public String decrypt(String encryptedText) {
        return basicTextEncryptor.decrypt(encryptedText);
    }
}
