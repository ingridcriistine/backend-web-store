package com.example.demo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryData;
import com.example.demo.dto.Token;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping("")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryRepository categoryRepo;

    @PostMapping("/category")
    public ResponseEntity<Object> createCategory(@RequestAttribute("token") Token token, @RequestBody CategoryData category) {
        
        var op = categoryRepo.findByName(category.name());

        if(category.name() == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }
        
        if (!op.isEmpty()) {
            return new ResponseEntity<>("Categoria já cadastrada no banco de dados!", HttpStatus.BAD_REQUEST);
        }

        categoryService.createCategory(category.name());
        return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Object> updateCategory(@RequestAttribute("token") Token token, @PathVariable Long id, @RequestBody CategoryData category) {
    
        var op = categoryRepo.findById(id);
        
        if(op == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        categoryService.updateCategory(id, category.name());
        return new ResponseEntity<>("Categoria atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Object> deleteCategory(@RequestAttribute("token") Token token, @PathVariable Long id) {
        
        var op = categoryRepo.findById(id);

        if(op.isEmpty()) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryData> getCategory(@PathVariable Long id) {
        
        var op = categoryRepo.findById(id);
        
        if(op.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        var category = categoryService.getCategory(id);
        return new ResponseEntity<>(new CategoryData(category.getId(), category.getName()), HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryData>> allCategories() {
        // Converte a lista de entidades Product para ProductData usando stream
        List<CategoryData> categoryDataList = categoryRepo.findAll().stream()
            .map(category -> new CategoryData(
                category.getId(),
                category.getName()
            ))
            .collect(Collectors.toList());
    
        return new ResponseEntity<>(categoryDataList, HttpStatus.OK);
    }
}
