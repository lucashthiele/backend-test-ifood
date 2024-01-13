package com.lucashthiele.backendtestifood.services;

import com.lucashthiele.backendtestifood.domain.category.Category;
import com.lucashthiele.backendtestifood.domain.category.CategoryDTO;
import com.lucashthiele.backendtestifood.exceptions.CategoryNotFoundException;
import com.lucashthiele.backendtestifood.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(CategoryDTO categoryData){
        var category = new Category(
                null,
                categoryData.title(),
                categoryData.description(),
                categoryData.ownerId()
        );

        categoryRepository.save(category);

        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){
        var category = getCategoryOrThrow(id);

        category.setTitle(categoryData.title());
        category.setDescription(categoryData.description());
        category.setOwnerId(categoryData.ownerId());

        categoryRepository.save(category);
        return category;
    }

    private Category getCategoryOrThrow(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));
    }

    public void delete(String id){
        categoryRepository.deleteById(id);
    }

}
