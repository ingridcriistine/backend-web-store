package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.Product;
import com.example.demo.repositories.CartProductRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.services.CartProductService;

public class CartProductImpl implements CartProductService {

    @Autowired
    CartProductRepository cartProductRepo;

    @Autowired
    CartRepository cartRepo;

    @Override
    public CartProduct createCartProduct(int quantity, Float totalPrice, Cart cart, Product product) {
        var findCart = cartRepo.findById(cart.getId());

        if(findCart == null) {
            return null;
        }

        var newCartProduct = new CartProduct();
        newCartProduct.setCart(cart);
        newCartProduct.setProduct(product);
        newCartProduct.setTotalPrice(totalPrice);
        newCartProduct.setQuantity(quantity); 

        return newCartProduct;

    }

    @Override
    public CartProduct updateCartProduct(Long id, int quantity, Float totalPrice, Cart cart, Product product) {
        var findCartProduct = cartProductRepo.findById(id);

        if(findCartProduct == null) {
            return null;
        }

        findCartProduct.get().setCart(cart);
        findCartProduct.get().setQuantity(quantity);
        findCartProduct.get().setProduct(product);
        findCartProduct.get().setTotalPrice(totalPrice);

        return findCartProduct.get();
    }

    @Override
    public CartProduct deleteCartProduct(Long id) {
        var cart = cartProductRepo.findById(id);
        
        if(cart.isEmpty()) {
            return null;
        }

        cartProductRepo.deleteById(id);

        return cart.get();
    }

    @Override
    public CartProduct getCartProduct(Long id) {
        var cart = cartProductRepo.findById(id);
        
        if(cart.isEmpty()) {
            return null;
        }

        return cart.get();
    }
}
