package com.example.springboot.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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
