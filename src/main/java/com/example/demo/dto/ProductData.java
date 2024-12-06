package com.example.demo.dto;

public record ProductData (
    String title,
    Float price,
    Boolean status,
    Long category
){}
