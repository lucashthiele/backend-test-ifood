package com.lucashthiele.backendtestifood.repositories;

import com.lucashthiele.backendtestifood.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
