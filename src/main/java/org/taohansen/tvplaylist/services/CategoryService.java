package org.taohansen.tvplaylist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taohansen.tvplaylist.entities.Category;
import org.taohansen.tvplaylist.repositories.CategoryRepository;
import org.taohansen.tvplaylist.services.exceptions.ResourceNotFoundException;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }
    public Category createCategory(Category Category) {
        return categoryRepository.save(Category);
    }
    public void deleteCategory(Long id) {
        Category Category = getCategoryById(id);
        categoryRepository.delete(Category);
    }
}
