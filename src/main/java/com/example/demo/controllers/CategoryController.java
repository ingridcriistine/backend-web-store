package com.example.demo.controllers;

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
    public ResponseEntity<Object> createCategory(@RequestAttribute("token") String token, @RequestBody String name) {
        
        var op = categoryService.createCategory(name);

        if(name == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }
        
        if (op == null) {
            return new ResponseEntity<>("Categoria já cadastrada no banco de dados!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
    }

    @PutMapping("category/{id}")
    public ResponseEntity<Object> updateCategory(@RequestAttribute("token") String token, @PathVariable Long id, @RequestBody String name) {
    
        var op = categoryService.updateCategory(id, name);
        
        if(op == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Categoria atualizada com sucesso!", HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Object> deleteCategory(@RequestAttribute("token") String token, @PathVariable Long id) {
        
        var op = categoryService.deleteCategory(id);

        if(op == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryData> getCategory(@PathVariable Long id) {
        
        var op = categoryService.getCategory(id);
        
        if(op == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CategoryData(op.getId(), op.getName()), HttpStatus.OK);
    }
}
