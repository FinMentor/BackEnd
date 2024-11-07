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
public class EncryptionServiceImpl implements EncryptionService {
    private BasicTextEncryptor basicTextEncryptor;

    public String encrypt(String plainText) {
        return basicTextEncryptor.encrypt(plainText);
    }

    public String decrypt(String encryptedText) {
        return basicTextEncryptor.decrypt(encryptedText);
    }
}
