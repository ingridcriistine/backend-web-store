package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CategoryData;
import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;

public class CategoryImpl implements  CategoryService{

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public ResponseEntity<Object> createCategory(String name) {
        var category = categoryRepo.findByName(name);

        if(name == null) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }
        
        if (!category.isEmpty()) {
            return new ResponseEntity<>("Categoria já cadastrada no banco de dados!", HttpStatus.BAD_REQUEST);
        }

        var newCategory = new Category();
        newCategory.setName(name);
        categoryRepo.saveAndFlush(newCategory);

        return new ResponseEntity<>("Categoria cadastrada com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryData> getCategory(Long id) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CategoryData(category.get().getId(), category.get().getName()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateCategory(Long id, String name) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        category.get().setName(name);
        categoryRepo.saveAndFlush(category.get());

        return new ResponseEntity<>("Categoria atualizada com sucesso!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCategory(Long id) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return new ResponseEntity<>("Categoria inválida!", HttpStatus.BAD_REQUEST);
        }

        categoryRepo.deleteById(id);

        return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
    }
    
}
