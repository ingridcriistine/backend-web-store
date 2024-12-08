package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductData;
import com.example.demo.dto.Token;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @PostMapping("/product")
    public ResponseEntity<Object> createProduct(@RequestAttribute("token") Token token, @RequestBody ProductData productData) {
        
        var op = categoryRepo.findById(productData.category());
        
        if(productData.title() == null || productData.price() == null || productData.status() == null) {
            return new ResponseEntity<>("Por favor, preencha corretamente todos os campos!", HttpStatus.BAD_REQUEST);
        }

        if(op.isEmpty()) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }
        
        productService.createProduct(productData.title(), productData.price(), productData.category(), productData.status());
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Object> updateProduct(@RequestAttribute("token") Token token, @PathVariable Long id, @RequestBody ProductData productData) {
    
        var op = productRepo.findById(id);

        if(op.isEmpty()) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        productService.updateProduct(id, productData.title(), productData.price(), productData.status(), productData.category());
        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@RequestAttribute("token") Token token, @PathVariable Long id) {
        
        var op = productRepo.findById(id);

        if(op == null) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Long id) {
        
        var op = productRepo.findById(id);
        
        if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        var product = productService.getProduct(id);
        return new ResponseEntity<>(new ProductData(product.getId(), product.getTitle(), product.getPrice(), product.getStatus(), product.getCategory().getId()), HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductData>> allProducts() {
        // Converte a lista de entidades Product para ProductData usando stream
        List<ProductData> productDataList = productRepo.findAll().stream()
            .map(product -> new ProductData(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getStatus(),
                product.getCategory().getId()
            ))
            .collect(Collectors.toList());
    
        return new ResponseEntity<>(productDataList, HttpStatus.OK);
    }

    @PatchMapping("product/{id}")
    public ResponseEntity<Object> setStatus(@RequestAttribute("token") String token, @PathVariable Long id, @RequestBody Boolean status) {
    
        var op = productRepo.findById(id);

        if(op == null) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        productService.setStatus(id, status);
        return new ResponseEntity<>("Status de produto atualizado com sucesso!", HttpStatus.OK);
	}
}
