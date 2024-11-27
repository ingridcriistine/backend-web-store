package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.model.Product;

public interface ProductService {
    Product createProduct(String title, Float price, Category category, Boolean status);
    Product getProduct(Long id);
    Product updateProduct(Long id);
    boolean deleteProduct(Long id);
}
