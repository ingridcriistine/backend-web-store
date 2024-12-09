package com.example.demo.services;

import com.example.demo.model.Product;

public interface ProductService {
    Product createProduct(String title, Float price, Long category, Boolean status, String image);
    Product updateProduct(Long id, String title, Float price, Boolean status, Long category, String image);
    Product deleteProduct(Long id);
    Product getProduct(Long id);
    Product setStatus(Long id, Boolean status);
}
