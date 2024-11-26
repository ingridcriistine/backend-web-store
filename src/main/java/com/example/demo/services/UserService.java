package com.example.demo.services;
import com.example.demo.model.UserModel;

public interface UserService{
    boolean checkPassword(String Password);
    UserModel create(String name, String email, String cpf, String password);
    UserModel authUser(String login, String password);
    boolean delete(Long id);
}