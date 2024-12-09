package com.example.demo.dto;

public record ProductData (
    Long id,
    String title,
    Float price,
    Boolean status,
    Long category,
    String image
){}
