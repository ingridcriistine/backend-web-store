package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartData;
import com.example.demo.dto.DeleteCart;
import com.example.demo.dto.Token;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartService;


@RestController
@RequestMapping("")
public class CartController {
    
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserRepository userRepo;

    // @PostMapping("/cart")
    // public ResponseEntity<Object> createCart(@RequestAttribute("token") Token token, @RequestBody CartData cartData) {
        
    //     var op = cartRepo.findByUser(cartData.user());

    //     if(op == null) {
    //         return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
    //     }

    //     cartService.createCart(cartData.user(), cartData.cartProduct(), cartData.totalPrice());
    //     return new ResponseEntity<>("Carrinho gerado com sucesso!", HttpStatus.OK);
    // }

    @PutMapping("cart/{id}")
    public ResponseEntity<Object> updateCart(@RequestAttribute("token") Token token, @RequestBody CartData cartData) {
        
        var user = userRepo.findById(token.getId());

        if(user.isEmpty()) {
            return new ResponseEntity<>("Usuário inválido!", HttpStatus.BAD_REQUEST);
        }
        
        var op = cartService.updateCart(cartData.id(), cartData.quantity(), cartData.product());

        if(op == null) {
            return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Object> deleteCart(@RequestAttribute("token") Token token, @PathVariable DeleteCart data) {

        var op = cartService.deleteCart(data.idCart(), data.idProduct());

         if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Carrinho deletado com sucesso!", HttpStatus.OK);

    }

    @GetMapping("/cart")
    public ResponseEntity<Object> getCart(@RequestAttribute("token") Token token) {
        
        var userOp = userRepo.findById(token.getId());
        var cartOp = cartRepo.findByUser(userOp.get());  
              
        if(cartOp == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        
        var cart = cartService.getCart(cartOp.get(0).getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
