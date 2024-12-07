package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Login;
import com.example.demo.dto.ProductData;
import com.example.demo.dto.SecurityToken;
import com.example.demo.dto.Token;
import com.example.demo.dto.UserData;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartService;
import com.example.demo.services.JWTService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping
public class UserController{

    @Autowired
    UserRepository repo;

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoderService encoder;

    @Autowired
    JWTService<Token> jwtService;

     @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepo;


    @PostMapping("/user")
    public ResponseEntity<String> create(@RequestBody UserData data){
        if(data.name().isEmpty() || data.email().isEmpty() || data.cpf().isEmpty() || data.password().isEmpty()){
            return new ResponseEntity<>("Os campos não podem ser vazios!", HttpStatus.BAD_REQUEST);
        }

        if(!service.validateEmail(data.email()))
        {
            return new ResponseEntity<>("Email inválido", HttpStatus.BAD_REQUEST);
        }
        
        if(!service.checkPassword(data.password()))
        {
            return new ResponseEntity<>("Senha deve ter no minímo 12 caracteres, letra maiuscula, letra minuscula e número", HttpStatus.BAD_REQUEST);
        }

      
        var user = service.create(data.name(), data.email(), data.cpf(), data.password(), data.account());
        cartService.createCart(user);
        return new ResponseEntity<>("Usuário cadastrado", HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<SecurityToken> Login(@RequestBody Login data) {

        if (data.login().isEmpty() || data.password().isEmpty()) {
            return new ResponseEntity<>(new SecurityToken(null, "Preenchas todos os campos"), HttpStatus.BAD_REQUEST);
        }

        UserModel Login = service.authUser(data.login(),data.password() );

        if (Login == null) {
            return new ResponseEntity<>(new SecurityToken(null, "Usuário não existe"), HttpStatus.CONFLICT);
        }

        if (!encoder.validatePass(data.password(), Login.getPassword())) {
            return new ResponseEntity<>(new SecurityToken(null, "Senha inválida"), HttpStatus.CONFLICT);
        }

        Token token = new Token();
        token.setId(Login.getId());

        var jwt = jwtService.get(token);

        return new ResponseEntity<>(new SecurityToken(jwt, "Sign In With Sucess"), HttpStatus.OK);

    }

    
    @GetMapping("/user/{id}")
    public ResponseEntity<UserData> getUser(@PathVariable Long id) {
        
        var op = productRepo.findById(id);
        
        if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        var product = productService.getProduct(id);
        return new ResponseEntity<>(new ProductData(product.getTitle(), product.getPrice(), product.getStatus(), product.getCategory().getId()), HttpStatus.OK);
    }

}