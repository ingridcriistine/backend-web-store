package com.example.demo.services;
import com.example.demo.model.UserModel;

public interface UserService{
    Boolean checkPassword(String password);
    Boolean validateEmail(String email);
    UserModel create(String name, String email, String cpf, String password, Boolean actvAccount);
    UserModel authUser(String login, String password);
    Boolean delete(Long id);
}