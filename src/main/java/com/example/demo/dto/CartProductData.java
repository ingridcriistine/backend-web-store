package com.example.demo.dto;

public record CartProductData (
    Long id,
    String product,
    int quantity,
    Float unityPrice,
    Float totalPrice
){}
