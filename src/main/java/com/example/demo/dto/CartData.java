package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.CartProduct;
import com.example.demo.model.UserModel;

public record CartData (
    UserModel user,  
    List<CartProduct> cartProduct,  
    Float totalPrice
){}
