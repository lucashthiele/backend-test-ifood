package com.lucashthiele.backendtestifood.domain.product;

import com.lucashthiele.backendtestifood.domain.category.Category;

public record ProductDTO(String title, String description, Double price, String categoryId, Integer ownerId) {
}
