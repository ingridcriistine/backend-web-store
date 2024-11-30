package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.UserModel;

public interface CartService {
    Cart createCart(UserModel user, List<CartProduct> cartProduct, Float totalPrice);
    Cart updateCart(Long id, UserModel user, List<CartProduct> cartProduct, Float totalPrice);
    Cart deleteCart(Long id);
    Cart getCart(Long id);
}
