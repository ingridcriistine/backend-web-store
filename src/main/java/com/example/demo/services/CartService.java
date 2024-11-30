package com.example.demo.services;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.UserModel;

public interface CartService {
    Cart createCart(UserModel user, CartProduct cartProduct, Float totalPrice);
    Cart updateCart(Long id, UserModel user, CartProduct cartProduct, Float totalPrice);
    Cart deleteCart(Long id);
    Cart getCart(Long id);
}
