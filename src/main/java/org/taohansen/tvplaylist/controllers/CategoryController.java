package org.taohansen.tvplaylist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.taohansen.tvplaylist.entities.Category;
import org.taohansen.tvplaylist.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategorys();
    }

    @GetMapping("/{id}")
    public Category getCategoryId(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    
    @PostMapping
    public Category createCategory(@RequestBody Category Category) {
        return categoryService.createCategory(Category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}