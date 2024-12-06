package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartData;
import com.example.demo.dto.Token;
import com.example.demo.repositories.CartRepository;
import com.example.demo.services.CartService;


@RestController
@RequestMapping("")
public class CartController {
    
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepo;

    @PostMapping("/cart")
    public ResponseEntity<Object> createCart(@RequestAttribute("token") Token token, @RequestBody CartData cartData) {
        
        var op = cartRepo.findByUser(cartData.user());

        if(op == null) {
            return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
        }

        cartService.createCart(cartData.user(), cartData.cartProduct(), cartData.totalPrice());
        return new ResponseEntity<>("Carrinho gerado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("cart/{id}")
    public ResponseEntity<Object> updateCart(@RequestAttribute("token") Token token, @PathVariable Long id, @RequestBody CartData cartData) {
        
        var op = cartService.createCart(cartData.user(), cartData.cartProduct(), cartData.totalPrice());

        if(op == null) {
            return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Object> deleteCart(@RequestAttribute("token") Token token, @PathVariable Long id) {

        var op = cartService.deleteCart(id);

         if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Carrinho deletado com sucesso!", HttpStatus.OK);

    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Object> getCart(@PathVariable Long id) {
        
        var op = cartService.getCart(id);
        
        if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CartData(op.getUser(), op.getCartProduct(), op.getTotalPrice()), HttpStatus.OK);
    }
    

    
}
