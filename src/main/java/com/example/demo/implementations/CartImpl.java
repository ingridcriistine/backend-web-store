package com.example.demo.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartService;

public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Cart createCart(UserModel user, List<CartProduct> cartProduct, Float totalPrice) {
        var findUser = userRepo.findById(user.getId());

        if(findUser == null) {
            return null;
        }

        var newCart = new Cart();
        newCart.setUser(user);
        newCart.setCartProduct(cartProduct);
        newCart.setTotalPrice(totalPrice);

        return newCart;
    }

    @Override
    public Cart updateCart(Long id, UserModel user, List<CartProduct> cartProduct, Float totalPrice) {
        var cart = cartRepo.findById(id);
        
        if(cart.isEmpty()) {
            return null;
        }

        cart.get().setTotalPrice(totalPrice);
        cart.get().setUser(user);
        cart.get().setCartProduct(cartProduct);
        cartRepo.saveAndFlush(cart.get());

        return cart.get();
    }

    @Override
    public Cart deleteCart(Long id) {
        var cart = cartRepo.findById(id);
        
        if(cart.isEmpty()) {
            return null;
        }

        cartRepo.deleteById(id);

        return cart.get();
    }

    @Override
    public Cart getCart(Long id) {
        var cart = cartRepo.findById(id);
        
        if(cart.isEmpty()) {
            return null;
        }

        return cart.get();
    }
    
}
