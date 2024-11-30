package com.example.demo.services;

import com.example.demo.model.Category;

public interface CategoryService {
    Category createCategory(String name);
    Category updateCategory(Long id, String name);
    Category deleteCategory(Long id);
    Category getCategory(Long id);
}
