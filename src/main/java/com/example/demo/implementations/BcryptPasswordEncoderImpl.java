package com.example.demo.implementations;

import com.example.demo.services.PasswordEncoderService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderImpl implements PasswordEncoderService {

    @Override
    public String encode(String password)
    {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public Boolean validatePass(String password, String passwordEncoded)
    {
        var bcrypt = new BCryptPasswordEncoder();

        if(!bcrypt.matches(password, passwordEncoded)) {
            return false;
        }
        return true;
    }

}