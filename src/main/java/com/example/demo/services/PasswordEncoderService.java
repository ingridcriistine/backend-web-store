package com.example.demo.services;

public interface PasswordEncoderService {
    String encode(String password);
    Boolean validatePass(String password, String passwordEncoder);
}