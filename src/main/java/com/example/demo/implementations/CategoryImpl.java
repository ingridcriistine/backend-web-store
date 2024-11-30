package com.example.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;

public class CategoryImpl implements  CategoryService{

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public Category createCategory(String name) {
        var category = categoryRepo.findByName(name);
        
        if (!category.isEmpty()) {
            return null;
        }

        var newCategory = new Category();
        newCategory.setName(name);
        categoryRepo.saveAndFlush(newCategory);

        return category.getFirst();
    }

    @Override
    public Category updateCategory(Long id, String name) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return null;
        }

        category.get().setName(name);
        categoryRepo.saveAndFlush(category.get());

        return category.get();
    }

    @Override
    public Category deleteCategory(Long id) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return null;
        }

        categoryRepo.deleteById(id);

        return category.get();
    }

    @Override
    public Category getCategory(Long id) {
        var category = categoryRepo.findById(id);
        
        if(category.isEmpty()) {
            return null;
        }

        return category.get();
    }
    
}
