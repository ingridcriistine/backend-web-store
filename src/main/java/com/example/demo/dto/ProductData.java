package com.example.demo.dto;

import com.example.demo.model.Category;

public record ProductData (
    String title,
    Float price,
    Boolean status,
    Long category
){}
