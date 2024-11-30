package com.example.demo.implementations;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.Product;
import com.example.demo.services.CartProductService;

public class CartProductImpl implements  CartProductService {

    @Override
    public CartProduct createCartProduct(int quantity, int totalPrice, Cart cart, Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CartProduct updateCartProduct(Long id, int quantity, int totalPrice, Cart cart, Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CartProduct deleteCartProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CartProduct getCartProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
