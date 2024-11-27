package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ProductData;
import com.example.demo.model.Category;
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
    public ResponseEntity<Object> createProduct(String title, Float price, Category category, Boolean status) {
        var findCategory = categoryRepo.findByName(category.getName());

        if(findCategory == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        if(title == null || price == null || status == null) {
            return new ResponseEntity<>("Por favor, preencha corretamente todos os campos!", HttpStatus.BAD_REQUEST);
        }

        var newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        newProduct.setCategory(category);
        newProduct.setStatus(status);
        productRepo.saveAndFlush(newProduct);

        return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateProduct(Long id, String title, Float price, Boolean status, Category category) {
        var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        product.get().setTitle(title);
        product.get().setPrice(price);
        product.get().setStatus(status);
        product.get().setCategory(category);
        productRepo.saveAndFlush(product.get());

        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {
        var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        productRepo.deleteById(id);

        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductData> getProduct(Long id) {
        var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ProductData(product.get().getId(), product.get().getTitle(), product.get().getPrice(), product.get().getStatus(), product.get().getCategory()), HttpStatus.OK);
    }

	@Override
	public ResponseEntity<Object> setStatus(Long id, Boolean status) {
		var product = productRepo.findById(id);
        
        if(product.isEmpty()) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        product.get().setStatus(status);
        productRepo.saveAndFlush(product.get());

        return new ResponseEntity<>("Status de produto atualizado com sucesso!", HttpStatus.OK);
	}
    
}
