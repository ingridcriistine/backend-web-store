package com.example.demo.services;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.UserModel;

public interface CartService {
    Cart createCart(UserModel user);
    Cart updateCart(Long id, int quantity, Product product);
    Cart deleteCart(Long idCart, Long idProduct);
    Cart getCart(Long id);
}
