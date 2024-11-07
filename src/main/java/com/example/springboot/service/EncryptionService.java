package com.example.springboot.service;

public interface EncryptionService {
    public String encrypt(String plainText);

    public String decrypt(String encryptedText);
}
