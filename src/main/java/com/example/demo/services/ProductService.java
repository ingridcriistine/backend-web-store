package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

public interface ProductService {
    Product createProduct(String title, Float price, Category category, Boolean status);
    Product updateProduct(Long id, String title, Float price, Boolean status, Category category);
    Product deleteProduct(Long id);
    Product getProduct(Long id);
    Product setStatus(Long id, Boolean status);
}
