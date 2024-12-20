package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

public class ProductImpl implements  ProductService {

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;
    
    @Override
    public Product createProduct(String title, Float price, Long category, Boolean status, String image) {
        var findCategory = categoryRepo.findById(category);

        if(findCategory == null) {
            return null;
        }

        var newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setCategory(findCategory.get());
        newProduct.setStatus(status);
        newProduct.setImage(image);
        productRepo.saveAndFlush(newProduct);

        return newProduct;
    }

    @Override
    public Product updateProduct(Long id, String title, Float price, Boolean status, Long category, String image) {
        var product = productRepo.findById(id);
        var findCategory = categoryRepo.findById(category);
        
        if(product.isEmpty()) {
            return null;
        }

        product.get().setTitle(title);
        product.get().setPrice(price);
        product.get().setStatus(status);
        product.get().setCategory(findCategory.get());
        product.get().setImage(image);
        productRepo.saveAndFlush(product.get());

        return product.get();
    }

    @Override
    public Product deleteProduct(Long id) {
        var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return null;
        }

        productRepo.deleteById(id);

        return product.get();
    }

    @Override
    public Product getProduct(Long id) {
        var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return null;
        }

        return product.get();
    }

	@Override
	public Product setStatus(Long id, Boolean status) {
		var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return null;
        }

        product.get().setStatus(status);
        productRepo.saveAndFlush(product.get());

        return product.get();
	}

}
