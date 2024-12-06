package com.example.demo.services;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.Product;

public interface CartProductService {
    CartProduct createCartProduct(int quantity, Cart cart, Product product);
    CartProduct updateCartProduct(Long id, int quantity, Float totalPrice, Cart cart, Product product);
    CartProduct deleteCartProduct(Long id);
    CartProduct getCartProduct(Long id);
}
