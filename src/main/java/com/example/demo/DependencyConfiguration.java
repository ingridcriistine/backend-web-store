package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.implementations.CartImpl;
import com.example.demo.implementations.CartProductImpl;
import com.example.demo.implementations.CategoryImpl;
import com.example.demo.implementations.DefaultJWTService;
import com.example.demo.implementations.ProductImpl;
import com.example.demo.implementations.UserImplements;
import com.example.demo.services.CartProductService;
import com.example.demo.services.CartService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.JWTService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

// import com.example.demo.services.UserService;
// import com.example.demo.implementations.UserImpl;

@Configuration
public class DependencyConfiguration {

    @Bean
    public JWTService jwtService(){
      return new DefaultJWTService();
    }

    @Bean
    public UserService userService() {
      return new UserImplements();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductImpl();
    } 

    @Bean
    public CartService cartService() {
      return new CartImpl();
    }

    @Bean
    public CartProductService cartProductService() {
      return new CartProductImpl();
    }

}