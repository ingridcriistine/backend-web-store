package com.example.demo.services;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.Product;

public interface CartProductService {
    CartProduct createCartProduct(int quantity, int totalPrice, Cart cart, Product product);
    CartProduct updateCartProduct(Long id, int quantity, int totalPrice, Cart cart, Product product);
    CartProduct deleteCartProduct(Long id);
    CartProduct getCartProduct(Long id);
}
