package com.example.demo.implementations;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.services.ProductService;

public class ProductImpl implements  ProductService {
    
    @Override
    public Product createProduct(String title, Float price, Category category) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product getProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product updateProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteProduct(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
