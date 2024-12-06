package com.example.demo.dto;

import com.example.demo.model.Product;

public record CartData (
    Long id, 
    int quantity, 
    Product product
){}
