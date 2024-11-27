package com.example.demo.dto;

import com.example.demo.model.Category;

public record ProductData (
    Long id,
    String title,
    Float price,
    Boolean status,
    Category category
){}
