package com.example.springboot.service;

public interface EncryptionService {
    // 암호화 메서드
    public String encrypt(String plainText);

    // 복호화 메서드
    public String decrypt(String encryptedText);
}
