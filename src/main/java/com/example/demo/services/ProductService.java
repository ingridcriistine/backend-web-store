package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ProductData;
import com.example.demo.model.Category;

public interface ProductService {
    ResponseEntity<Object> createProduct(String title, Float price, Category category, Boolean status);
    ResponseEntity<Object> updateProduct(Long id, String title, Float price, Boolean status, Category category);
    ResponseEntity<Object> deleteProduct(Long id);
    ResponseEntity<ProductData> getProduct(Long id);
    ResponseEntity<Object> setStatus(Long id, Boolean status);
}
