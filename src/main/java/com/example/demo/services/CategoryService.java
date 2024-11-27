package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CategoryData;

public interface CategoryService {
    ResponseEntity<Object> createCategory(String name);
    ResponseEntity<Object> updateCategory(Long id, String name);
    ResponseEntity<Object> deleteCategory(Long id);
    ResponseEntity<CategoryData> getCategory(Long id);
}
