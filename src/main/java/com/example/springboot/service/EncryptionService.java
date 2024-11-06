package com.example.springboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EncryptionService {
    private BasicTextEncryptor textEncryptor;

    // 암호화 메서드
    public String encrypt(String plainText) {
        return textEncryptor.encrypt(plainText);
    }

    // 복호화 메서드
    public String decrypt(String encryptedText) {
        return textEncryptor.decrypt(encryptedText);
    }
}
