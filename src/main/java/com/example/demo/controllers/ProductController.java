package com.example.demo.controllers;

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
import com.example.demo.model.Category;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("")
public class ProductController {
    
    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Object> createProduct(@RequestAttribute("token") String token, @RequestBody String name, @RequestBody Float price, @RequestBody Boolean status, @RequestBody Category category) {
        
        var op = productService.createProduct(name, price, category, status);
        
        if(name == null || price == null || status == null) {
            return new ResponseEntity<>("Por favor, preencha corretamente todos os campos!", HttpStatus.BAD_REQUEST);
        }

        if(op == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.OK);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Object> updateProduct(@RequestAttribute("token") String token, @PathVariable Long id, @RequestBody String name, @RequestBody Float price, @RequestBody Boolean status, @RequestBody Category category) {
    
        var op = productService.updateProduct(id, name, price, status, category);

        if(op == null) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@RequestAttribute("token") String token, @PathVariable Long id) {
        
        var op = productService.deleteProduct(id);

        if(op == null) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductData> getProduct(@PathVariable Long id) {
        
        var op = productService.getProduct(id);
        
        if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ProductData(op.getId(), op.getTitle(), op.getPrice(), op.getStatus(), op.getCategory()), HttpStatus.OK);
    }

    @PatchMapping("product/{id}")
    public ResponseEntity<Object> setStatus(@RequestAttribute("token") String token, @PathVariable Long id, @RequestBody Boolean status) {
    
        var op = productService.setStatus(id, status);

        if(op == null) {
            return new ResponseEntity<>("Produto inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Status de produto atualizado com sucesso!", HttpStatus.OK);
	}
}
