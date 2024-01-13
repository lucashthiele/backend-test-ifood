package com.lucashthiele.backendtestifood.controllers;

import com.lucashthiele.backendtestifood.domain.category.Category;
import com.lucashthiele.backendtestifood.domain.category.CategoryDTO;
import com.lucashthiele.backendtestifood.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Transactional
    public ResponseEntity<Category> create(
            @RequestBody CategoryDTO categoryData,
            UriComponentsBuilder uriBuilder
    ){
        var category = categoryService.create(categoryData);

        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> read(){
        var categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Category> update(
            @PathVariable String id,
            @RequestBody CategoryDTO categoryData
    ){
        var category = categoryService.update(id, categoryData);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable String id){
        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
