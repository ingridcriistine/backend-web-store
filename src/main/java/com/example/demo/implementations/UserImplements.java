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
    public Boolean checkPassword(String password) {
        if (password.length() < 12) {
            return false;
        }
        //? Regex que testa se tem Minuscula, Maiuscula e Numerico em qualquer parte da String, veja em Regex101.com se quiser testar
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[1-9]).+$")) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean validateEmail(String email) {
        //ainda é preciso fazer a validação do email
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserModel create(String name, String email, String cpf, String password, Boolean actvAccount) {

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
        newUser.setActvAccount(true);

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
    public Boolean delete(Long id) {
        var checkId = repo.findById(id);

        if(checkId.isEmpty()){
            return false;
        }

        //PRECISARÁ PEGAR O USUÁRIO DO ID INFORMADO PARA MUDAR O ATRIBUTO DE CONTA
        return true;
    }


 
}