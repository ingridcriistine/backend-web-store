package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;


public class UserImplements implements UserService{
    @Autowired
    UserRepository repo;

    @Override
    public boolean checkPassword(String Password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkPassword'");
    }

    @Override
    public UserModel create(String name, String email, String cpf, String password) {

        if (!repo.findByEmail(email).isEmpty()) {
            return null;
        }

        if (!repo.findByCpf(cpf).isEmpty()) {
            return null;
        }

        UserModel newUser = new UserModel();
        var encoder = new BCryptPasswordEncoder();

        newUser.setCpf(cpf);
        newUser.setEmail(email);
        newUser.setName(name);

        //? Salvado com BCrypt
        newUser.setPassword(encoder.encode(password));
        repo.save(newUser);

        return newUser;
    }

    @Override
    public UserModel authUser(String login, String password) {
        
        if(repo.findByEmail(login).isEmpty()) {
            return null;
        }

        UserModel auth = repo.findByEmail(login).get(0);

        return auth;
    }

    @Override
    public boolean delete(Long id) {
        var checkId = repo.findById(id);

        if(checkId.isEmpty()){
            return false;
        }
        
        return true;
    }

 
}