package com.example.demo.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Cart;
import com.example.demo.model.CartProduct;
import com.example.demo.model.Product;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.CartProductRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CartService;

public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CartProductRepository cartProductRepo;

    @Autowired
    ProductRepository productRepo;

    @Override
    public Cart createCart(UserModel user) {
        var findUser = userRepo.findById(user.getId());

        if(findUser.isEmpty()) {
            return null;
        }

        var newCart = new Cart();
        newCart.setUser(user);
        newCart.setCartProduct(new ArrayList<>());
        newCart.setTotalPrice(0.f);
        cartRepo.saveAndFlush(newCart);

        return newCart;
    }

    @Override
    public Cart updateCart(Long id, int quantity, Product product) {
        var cartOp = cartRepo.findById(id);
        
        if(cartOp.isEmpty()) {
            return null;
        }
        
        var cart = cartOp.get();

        for (CartProduct cartProduct : cart.getCartProduct()) {
            if(cartProduct.getProduct().equals(product)) {
                cartProduct.setQuantity(cartProduct.getQuantity()+quantity);
                cartProduct.setTotalPrice(cartProduct.getTotalPrice() + (product.getPrice() * quantity));
                cart.setTotalPrice(cart.getTotalPrice() + cartProduct.getTotalPrice());
                cartRepo.save(cart);

                return cart;
            }
        } 

        var newCartProduct = new CartProduct();
        newCartProduct.setCart(cart);
        newCartProduct.setProduct(product);
        newCartProduct.setTotalPrice(product.getPrice() * quantity);
        newCartProduct.setQuantity(quantity); 
        cartProductRepo.saveAndFlush(newCartProduct);

        var addProduct = cart.getCartProduct();
        addProduct.add(newCartProduct);
        cart.setCartProduct(addProduct);
        cart.setTotalPrice(cart.getTotalPrice() + newCartProduct.getTotalPrice());
        cartRepo.save(cart);

        return cart;
    }

    @Override
    public Cart deleteCart(Long idCart, Long idProduct) {
        var cartOp = cartRepo.findById(idCart);
        var productOp = productRepo.findById(idProduct);
        
        if(cartOp.isEmpty() || productOp.isEmpty()) {
            return null;
        }
        
        var cart = cartOp.get();
        var product = productOp.get();
        CartProduct temp = null;

        for (CartProduct cartProduct : cart.getCartProduct()) {
            if(cartProduct.getProduct().equals(product)) {
                temp = cartProduct;
                break;
            }
        }

        if (temp == null) {
            return null;
        }
        
        var delProduct = cart.getCartProduct();
        cart.setTotalPrice(cart.getTotalPrice() - temp.getTotalPrice());
        delProduct.remove(temp);
        cartProductRepo.deleteById(temp.getId());
        cart.setCartProduct(delProduct);
        cartRepo.saveAndFlush(cart);

        return cart;
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
