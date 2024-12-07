package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import com.example.demo.dto.CartProductData;
import com.example.demo.dto.ProductData;
import com.example.demo.dto.Token;
import com.example.demo.model.CartProduct;
import com.example.demo.repositories.CartProductRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartProductService;
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

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CartProductRepository cartProRepo;

    @Autowired
    CartProductService cartProService;

    // @PostMapping("/cart")
    // public ResponseEntity<Object> createCart(@RequestAttribute("token") Token token, @RequestBody CartData cartData) {
        
    //     var op = cartRepo.findByUser(cartData.user());

    //     if(op == null) {
    //         return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
    //     }

    //     cartService.createCart(cartData.user(), cartData.cartProduct(), cartData.totalPrice());
    //     return new ResponseEntity<>("Carrinho gerado com sucesso!", HttpStatus.OK);
    // }

    @PutMapping("/cart")
    public ResponseEntity<Object> updateCart(@RequestAttribute("token") Token token, @RequestBody CartData cartData) {
        
        var user = userRepo.findById(token.getId());
        var product = productRepo.findById(cartData.product());

        if(user.isEmpty()) {
            return new ResponseEntity<>("Usuário inválido!", HttpStatus.BAD_REQUEST);
        }

        if(product.isEmpty()) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }
        
        var op = cartService.updateCart(token.getId(), cartData.quantity(), product.get());

        if(op == null) {
            return new ResponseEntity<>("Carrinho inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Carrinho atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Object> deleteCart(@RequestAttribute("token") Token token, @PathVariable Long id) {

        var op = productRepo.findById(id);

        if(op.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        cartService.deleteCart(token.getId(), id);
        return new ResponseEntity<>("Produto removido do carrinho!", HttpStatus.OK);

    }

    @GetMapping("/cart")
    public ResponseEntity<Object> getCart(@RequestAttribute("token") Token token) {
        
        var userOp = userRepo.findById(token.getId());
        var cartOp = cartRepo.findByUser(userOp.get());  
        var cartProductOp = cartProRepo.findByCart(cartOp.get(0));
              
        if(cartProductOp == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List<CartProductData> productDataList = cartProRepo.findAll().stream()
            .map(cartProduct -> new CartProductData(
                cartProduct.getId(),
                cartProduct.getQuantity(),
                cartProduct.getTotalPrice()
            ))
            .collect(Collectors.toList());
        
        var cart = cartService.getCart(cartOp.get(0).getId());
        return new ResponseEntity<>(productDataList, HttpStatus.OK);
    }
}
